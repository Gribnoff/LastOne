package ru.gribnoff.l5.t1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

class StudentDAOImpl implements StudentDAO{
	private final SessionFactory sessionFactory;

	public StudentDAOImpl() {
		this.sessionFactory = SessionFactoryUtil.getSessionFactory();
	}

	public StudentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Student student) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
		}
	}

	@Override
	public void save(List<Student> students) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			students.forEach(session::save);
			transaction.commit();
		}
	}

	@Override
	public void update(Student student) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(student);
			transaction.commit();
		}
	}

	@Override
	public void update(List<Student> students) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			students.forEach(session::update);
			transaction.commit();
		}
	}

	@Override
	public void delete(Student student) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(student);
			transaction.commit();
		}
	}

	@Override
	public void delete(List<Student> students) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			students.forEach(session::delete);
			transaction.commit();
		}
	}

	@Override
	public Optional<Student> findBiId(Long id) {
		Session session = sessionFactory.openSession();
		Student student = session.get(Student.class, id);
		session.close();
		return Optional.ofNullable(student);
	}

	@Override
	public Optional<List<Student>> findAll() {
		Session session = sessionFactory.openSession();
		List<Student> students = (List<Student>) session.createQuery("From Student").list();
		session.close();
		return Optional.ofNullable(students);
	}
}
