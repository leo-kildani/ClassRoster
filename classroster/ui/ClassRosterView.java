package classroster.ui;

import java.util.List;
import classroster.dto.Student;

public class ClassRosterView {
	private UserIO io;
	
	public ClassRosterView(UserIO io) {
		this.io = io;
	}
	
	public int getMenuSelection() {
		io.print("1. List Students");
		io.print("2. Create New Student");
		io.print("3. View A Student");
		io.print("4. Remove A Student");
		io.print("5. Exit");
		
		return io.readInt("Please select from the above choices (1-5): ", 1, 5);
	}
	
	public Student getNewStudentInfo() {
		Student newStudent = new Student(io.readString("Enter Student ID: "));
		newStudent.setFirstName(io.readString("Enter Student First Name: "));
		newStudent.setLastName(io.readString("Enter Student Last Name: "));
		newStudent.setCohort(io.readString("Enter Student Cohort: "));
		return newStudent;
	}
	
	public void displayStudentRoster(List<Student> studentList) {
		if (studentList.isEmpty())
			io.print("XXXEMPTYXXX");
		else
			studentList.forEach(s -> io.print(s.toString()));
	}
	
	public String getStudentID() {
		return io.readString("Enter Student ID: ");
	}
	
	public void displayStudentInfo(Student student) {
		if (student != null)
			io.print(student.toString());
		else
			io.print("Student Not Found");
	}
	
	public void displayRemoveStudent(Student student) {
		if (student != null) {
			displayStudentInfo(student);
			io.print("This student has been removed");
		}
		else
			io.print("Student Not Found");
	}
	
	public void displayErrorMessage(String err) {
		displayBanner("ERROR");
		io.print(err);
	}
	
	public void displayExitMessage() {
		io.print("Goodbye!");
		io.close();
	}
	
	public void displayBanner(String header) {
		io.print("=== " + header + " ===");
	}
	
	public void displayActionSuccess(String action) {
		io.print(action);
		io.readString("Click SPACE to continue.", "");
	}
	
}
