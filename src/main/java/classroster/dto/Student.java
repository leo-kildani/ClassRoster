package classroster.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

@Entity(name = "Student")
@Table(name = "students")
public @Data class Student {
	@Id
	@Column(name = "student_id")
	private Integer studentID;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "cohort")
	private String cohort;
	
	public Student() {
		studentID = generateID();
	}

	// FOR FILE IMPL
	public Student(int id) {
		studentID = id;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s, %s (%s)", studentID, lastName, firstName, cohort);
	}

	public String[] toArray() {
		return new String[] {
				String.valueOf(studentID),
				firstName,
				lastName,
				cohort
		};
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

	// generate random id between 1000-9999
	public static int generateID(){
		return (int)Math.floor(Math.random() * (9000) + 1000);
	}

	public enum CompareStudent implements Comparator<Student> {
		FIRST_NAME{
			@Override
			public int compare(Student s1, Student s2) {
				return Integer.compare(s1.firstName.compareTo(s2.firstName), 0);
			}
		},
		
		LAST_NAME{
			@Override
			public int compare(Student s1, Student s2) {
				return Integer.compare(s1.lastName.compareTo(s2.lastName), 0);
			}
		},
		
		COHORT{
			@Override
			public int compare(Student s1, Student s2) {
				return Integer.compare(s1.cohort.compareTo(s2.cohort), 0);
			}
		}
	}
}
