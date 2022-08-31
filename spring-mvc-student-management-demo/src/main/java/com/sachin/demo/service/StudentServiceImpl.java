package com.sachin.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sachin.demo.model.Student;
import com.sachin.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(int studentId) {
		return studentRepository.findById(studentId).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getByStudentFirstName(String firstName) {
		return studentRepository.getByStudentFirstName(firstName);
	}

	@Override
	public List<Student> getByStudentLastName(String lastName) {
		return studentRepository.getByStudentLastName(lastName);
	}

	@Override
	public List<Student> getByGender(String gender) {
		return studentRepository.getByGender(gender);
	}

	@Override
	public List<Student> getByStream(String stream) {
		return studentRepository.getByStream(stream);
	}

	@Override
	public void deleteStudent(int studentId) {
		studentRepository.deleteById(studentId);
	}

}
