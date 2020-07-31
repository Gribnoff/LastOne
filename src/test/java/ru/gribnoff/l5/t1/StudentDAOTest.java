package ru.gribnoff.l5.t1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

class StudentDAOTest {
	private SessionFactory sessionFactory;
	private Session session;
	private StudentDAO studentDAO;

	@BeforeEach
	public void before() {
		sessionFactory = createSessionFactory();
		session = sessionFactory.openSession();
		studentDAO = new StudentDAOImpl(sessionFactory);

		Random random = new Random();
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			students.add(new Student("Student №" + (i + 1), (random.nextInt(5)) + 1));
		}
		studentDAO.save(students);
	}

	@Test
	void findAllTest() {
		Assertions.assertEquals(1000, studentDAO.findAll().orElse(new ArrayList<>()).size());
		studentDAO.save(new Student("John Doe", 5));
		Assertions.assertEquals(1001, studentDAO.findAll().orElse(new ArrayList<>()).size());
	}

	@Test
	void saveTess() {
		studentDAO.save(new Student("John Doe", 5));
		Assertions.assertEquals(1001, studentDAO.findAll().orElse(new ArrayList<>()).size());
	}

	@Test
	void findByIdTest() {
		Optional<Student> studentOpt = studentDAO.findBiId(385L);
		Assertions.assertTrue(studentOpt.isPresent());
		Assertions.assertEquals(385L, studentOpt.get().getId());
	}

	@Test
	void updateTest() {
		Student student = new Student("John Doe", 5);
		studentDAO.save(student);

		student.setName("Jane Doe");
		studentDAO.update(student);
		Assertions.assertEquals("Jane Doe", studentDAO.findBiId(1001L).orElse(new Student(null, 1)).getName());
	}

	@Test
	void deleteTest() {
		Student student = studentDAO.findBiId(385L).orElse(new Student());
		studentDAO.delete(student);
		Assertions.assertEquals(999, studentDAO.findAll().orElse(new ArrayList<>()).size());
	}

	@AfterEach
	public void after() {
		session.close();
		sessionFactory.close();
	}

	private SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Student.class);
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create");
		return configuration.buildSessionFactory();
	}
}
