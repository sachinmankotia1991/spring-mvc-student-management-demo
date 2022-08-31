package com.sachin.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> getByStudentFirstName(String firstName);

	List<Student> getByStudentLastName(String lastName);

	List<Student> getByGender(String gender);
	
	List<Student> getByStream(String gender);

}
