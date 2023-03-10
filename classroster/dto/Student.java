package classroster.dto;

import java.util.Objects;
import java.util.Random;

public class Student {
	private String firstName, lastName;
	private final String studentID;
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
	
	@Override
	public String toString() {
		return String.format("%s: %s, %s (%s)", studentID, lastName, firstName, cohort);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cohort, firstName, lastName, studentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(cohort, other.cohort) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(studentID, other.studentID);
	}

}
