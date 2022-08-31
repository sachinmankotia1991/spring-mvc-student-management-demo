package com.sachin.demo.service;

import java.util.List;

import com.sachin.demo.model.Student;

public interface StudentService {
	 List<Student> getAllStudents();

	 Student saveStudent(Student student);

	 Student getStudentById(int studentId);

	 Student updateStudent(Student student);

	List<Student> getByStudentFirstName(String firstName);

	List<Student> getByStudentLastName(String lastName);

	List<Student> getByGender(String gender);
	
	List<Student> getByStream(String stream);
	
	void deleteStudent(int studentId);

}
