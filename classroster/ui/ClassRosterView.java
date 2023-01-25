package classroster.ui;

import java.util.List;
import classroster.dto.Student;

public class ClassRosterView {
	private UserIO io = new UserIOConsoleImpl();
	
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
		studentList.forEach(s -> io.print(s.toString()));
	}
	
	public void displayBanner(String header) {
		io.print("=== " + header + " ===");
	}
	
	public void displayActionSuccess(String action) {
		io.readString(action + " Please hit ENTER to continue.");
	}
	
}
