package ru.gribnoff.l7.t1;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface StudentRepository extends CrudRepository<Student, Integer> {
	List<Student> findAll();
}
