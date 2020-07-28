package ru.gribnoff.l4.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionRepository<T> {
	private final DbConn dbConn;

	private final Class<T> clazz;
	private Field fieldId;
	private List<Field> fields;
	private Method getId;
	private Method setId;
	private List<Method> getters;
	private List<Method> setters;

	private String queryFindById;
	private String queryInsert;
	private String queryUpdate;
	private String queryDelete;

	public ReflectionRepository(Class<T> clazz, DbConn dbConn) throws NoSuchMethodException {
		this.clazz = clazz;
		this.dbConn = dbConn;
		this.init();
	}

	public void init() throws NoSuchMethodException {
		if (!clazz.isAnnotationPresent(DbTable.class))
			throw new RuntimeException("Invvalid class structure, @DbTable is missing");

		String tableName = clazz.getAnnotation(DbTable.class).name();
		Field[] allFields = clazz.getDeclaredFields();
		fields = new ArrayList<>();
		getters = new ArrayList<>();
		setters = new ArrayList<>();

		for (Field field : allFields) {
			if (field.isAnnotationPresent(DbId.class)) {
				if (fieldId == null)
					fieldId = field;
				else
					throw new RuntimeException("Invvalid class structure, too many @DbId");
			}

			if (field.isAnnotationPresent(DbField.class)) {
				fields.add(field);
				getters.add(clazz.getDeclaredMethod(generateGetterName(field.getName())));
				setters.add(clazz.getDeclaredMethod(generateSetterName(field.getName()), field.getType()));
			}
		}
		getId = clazz.getDeclaredMethod("getId");
		setId = clazz.getDeclaredMethod("setId", Long.class);

		//SELECT * FROM table_name WHERE id = ?
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(tableName).append(" where id = ?");
		queryFindById = sb.toString();
		sb.setLength(0);

		//INSERT INTO table_name (column_name...) VALUES (?...)
		sb.append("insert into ").append(tableName).append(" (");
		for (Field field : fields) {
			sb.append(field.getName()).append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(") values (");
		sb.append("?, ".repeat(fields.size()));
		sb.setLength(sb.length() - 2);
		sb.append(");");
		queryInsert = sb.toString();
		sb.setLength(0);

		//UPDATE table_name SET column_name = ?... WHERE id = ?
		sb.append("update ").append(tableName).append(" set ");
		for (Field field : fields) {
			sb.append(field.getName()).append(" = ?, ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(" where id = ?");
		queryUpdate = sb.toString();
		sb.setLength(0);

		//DELETE FROM table_name WHERE id = ?
		sb.append("delete from ").append(tableName).append(" where id = ?");
		queryDelete = sb.toString();
		sb.setLength(0);
	}

	public void saveOrUpdate(T obj) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
		Object[] args;
		String query;
		Long id = (Long) getId.invoke(obj);
		if (findById(id) == null) {
			args = new Object[fields.size()];
			query = queryInsert;
		} else {
			args = new Object[fields.size() + 1];
			args[args.length - 1] = id;
			query = queryUpdate;
		}
		for (int i = 0; i < fields.size(); i++) {
			args[i] = getters.get(i).invoke(obj);
		}

		dbConn.execute(query, args);
	}

	public T findById(Long id) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		T obj = clazz.getConstructor().newInstance();
		try (ResultSet rs = dbConn.select(queryFindById, id)) {
			if (rs.next()) {
				setId.invoke(obj, rs.getLong("id"));
				for (int i = 0; i < fields.size(); i++) {
					setters.get(i).invoke(obj, rs.getObject(fields.get(i).getName()));
				}
			} else {
				return null;
			}
		}

		return obj;
	}

	public boolean delete(Long id) throws SQLException {
		return dbConn.execute(queryDelete, id) > 0;
	}

	private String generateGetterName(String filedName) {
		return "get" + Character.toUpperCase(filedName.charAt(0)) + filedName.substring(1);
	}

	private String generateSetterName(String filedName) {
		return "set" + Character.toUpperCase(filedName.charAt(0)) + filedName.substring(1);
	}
}
