package classroster.dto;

import java.util.Random;

public class Student {
	private String firstName, lastName;
	final String studentID;
	private String cohort;
	
	public Student(String id) {
		studentID = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCohort() {
		return cohort;
	}

	public void setCohort(String cohort) {
		this.cohort = cohort;
	}

	public String getStudentID() {
		return studentID;
	}

}
