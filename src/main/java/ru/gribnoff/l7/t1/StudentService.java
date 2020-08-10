package ru.gribnoff.l7.t1;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
class StudentService {
	private final StudentRepository studentRepository;

	StudentService(@NotNull StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	Student findById(Integer id) throws NotFoundException {
		return studentRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Student with id=" + id + " not found!")
		);
	}

	List<Student> findAll() {
		return studentRepository.findAll();
	}

	void save(StudentDTO studentDTO) {
		studentRepository.save(new Student(studentDTO.getName(), studentDTO.getAge()));
	}

	void update(Student student) {
		studentRepository.save(student);
	}

	void deleteById(Integer id) {
		studentRepository.deleteById(id);
	}

	void delete(Student student) {
		studentRepository.delete(student);
	}
}
