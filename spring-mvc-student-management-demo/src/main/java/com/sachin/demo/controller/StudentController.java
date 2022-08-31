package com.sachin.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sachin.demo.model.Student;
import com.sachin.demo.service.StudentService;

@Controller
public class StudentController {
	private StudentService studentService;

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	/**
	 * This method is called when we reload the page or open it for the first time .
	 * Page will all students will be opened.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/students")
	public String allStudents(Model model) {
		LOGGER.info("inside StudentController.allStudents method");
		List<Student> listStudents = studentService.getAllStudents();
		model.addAttribute("students", listStudents);
		return "students";
	}

	/**
	 * This method is called when we click on new student page. Empty page to add
	 * new student will be opened.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		LOGGER.info("inside StudentController.createStudentForm method");
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";

	}

	/**
	 * This method is called when we click on student search. Empty page to search a
	 * student will be opened.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/students/search")
	public String searchStudentForm(Model model) {
		LOGGER.info("inside StudentController.searchStudentForm method");
		Student student = new Student();
		model.addAttribute("student", student);
		return "search_student";

	}

	/**
	 * This method is called when we click on save student page. Once new student
	 * data is added and save button is clicked, data will be saved in database and
	 * page will be redirected to all students page.
	 * 
	 * @param student
	 * @return
	 */
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";

	}

	/**
	 * This method is called when we click on Update link for a record it will open
	 * page will current info
	 * 
	 * @param studentId
	 * @param model
	 * @return
	 */
	@GetMapping("/students/edit/{studentId}")
	public String editStudentForm(@PathVariable int studentId, Model model) {
		LOGGER.info("inside StudentController.editStudentForm method and studentId is :" + studentId);
		model.addAttribute("student", studentService.getStudentById(studentId));
		return "edit_student";

	}

	/**
	 * This method is called when we click on save after updating a record
	 * 
	 * @param studentId
	 * @param student
	 * @param model
	 * @return
	 */
	@PostMapping("/students/{studentId}")
	public String updateStudent(@PathVariable int studentId, @ModelAttribute("student") Student student, Model model) {
		LOGGER.info("inside StudentController.updateStudent method and studentId is :" + studentId);
		// get student details from db
		Student currentStudent = studentService.getStudentById(studentId);
		currentStudent.setStudentId(studentId);
		currentStudent.setStudentFirstName(student.getStudentFirstName());
		currentStudent.setStudentLastName(student.getStudentLastName());
		currentStudent.setAddress(student.getAddress());
		currentStudent.setDateOfBirth(student.getDateOfBirth());
		currentStudent.setGender(student.getGender());
		currentStudent.setStream(student.getStream());
		// saving
		studentService.saveStudent(currentStudent);
		return "redirect:/students";

	}

	/**
	 * Method to search student details based on key-value search parameters
	 * 
	 * @param model
	 * @param searchoption
	 * @param keyword
	 * @return
	 */
	@GetMapping("/students/search/result")
	public String searchStudents(Model model, String searchoption, String keyword) {
		LOGGER.info("inside StudentController.searchStudents method and searchoption is :" + searchoption);
		LOGGER.info("inside StudentController.searchStudents method and keyword is :" + keyword);
		List<Student> listStudents = null;
		if (searchoption.equalsIgnoreCase("FirstName")) {
			listStudents = studentService.getByStudentFirstName(keyword);
		} else if (searchoption.equalsIgnoreCase("LastName")) {
			listStudents = studentService.getByStudentLastName(keyword);
		} else if (searchoption.equalsIgnoreCase("Gender")) {
			listStudents = studentService.getByGender(keyword);
		} else if (searchoption.equalsIgnoreCase("Stream")) {
			listStudents = studentService.getByStream(keyword);
		}
		model.addAttribute("students", listStudents);
		return "students";

	}

	@GetMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable int id) {
		LOGGER.info("inside StudentController.deleteStudent method and studentId is :" + id);
		studentService.deleteStudent(id);
		return "redirect:/students";
	}

}
