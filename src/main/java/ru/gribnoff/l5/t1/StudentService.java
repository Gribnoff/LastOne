package ru.gribnoff.l5.t1;

import java.util.List;
import java.util.Optional;

class StudentService {
	private final StudentDAO studentDAO;

	public StudentService() {
		this.studentDAO = new StudentDAOImpl();
	}

	public void save(Student student) {
		studentDAO.save(student);
	}

	public void save(List<Student> students) {
		studentDAO.save(students);
	}

	public void update(Student student) {
		studentDAO.update(student);
	}

	public void update(List<Student> students) {
		studentDAO.update(students);
	}

	public void delete(Student student) {
		studentDAO.delete(student);
	}

	public void delete(List<Student> students) {
		studentDAO.delete(students);
	}

	public Optional<Student> findBiId(Long id) {
		return studentDAO.findBiId(id);
	}

	public Optional<List<Student>> findAll() {
		return studentDAO.findAll();
	}
}
