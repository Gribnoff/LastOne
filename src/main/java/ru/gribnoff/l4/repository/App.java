package ru.gribnoff.l4.repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class App {
	public static void main(String[] args) {
		DbConn dbConn = new DbConn();
		try {
			dbConn.connect();
			ReflectionRepository<Product> productRepository = new ReflectionRepository<>(Product.class, dbConn);
			Product product = new Product(2L, "donut", 150.);
			productRepository.saveOrUpdate(product);

			Product productFromDb = productRepository.findById(2L);
			System.out.println(productFromDb);
			System.out.println(productRepository.delete(1L));
		} catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException throwables) {
			throwables.printStackTrace();
		} finally {
			dbConn.disconnect();
		}
	}
}
