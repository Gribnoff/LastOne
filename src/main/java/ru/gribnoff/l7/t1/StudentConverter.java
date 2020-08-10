package ru.gribnoff.l7.t1;

import org.springframework.stereotype.Component;

@Component
class StudentConverter {
	Student dtoToStudent(StudentDTO studentDTO) {
		return new Student(studentDTO.getName(), studentDTO.getAge());
	}
}
