package com.sachin.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;

	@Column(name = "first_name")
	private String studentFirstName;

	@Column(name = "last_name")
	private String studentLastName;

	@Column(name = "dob")
	private String dateOfBirth;

	private String gender;
	private String address;
	private String stream;

}
