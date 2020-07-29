package ru.gribnoff.l5.t1;

import java.util.List;
import java.util.Optional;

interface StudentDAO {
	void save(Student student);
	void save(List<Student> students);
	void update(Student student);
	void update(List<Student> students);
	void delete(Student student);
	void delete(List<Student> students);
	Optional<Student> findBiId(Long id);
	Optional<List<Student>> findAll();
}
