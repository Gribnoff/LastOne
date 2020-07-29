package ru.gribnoff.l5.t1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class App {
	public static void main(String[] args) {
		StudentService studentService = new StudentService();
		Random random = new Random();

		List<Student> students = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			students.add(new Student("Student №" + (i + 1), (random.nextInt(5)) + 1));
		}
		studentService.save(students);

		System.out.println(studentService.findAll().orElse(new ArrayList<>()).size());
		studentService.save(new Student("John Doe", 4));
		System.out.println(studentService.findAll().orElse(new ArrayList<>()).size());
		Student student = studentService.findBiId(386L).orElse(new Student());
		System.out.println(student);
		student.setName("Jane Doe");
		studentService.update(student);
		System.out.println(student);
		studentService.delete(student);
		System.out.println(studentService.findAll().orElse(new ArrayList<>()).size());
	}
}
