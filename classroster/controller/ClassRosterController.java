package classroster.controller;

import classroster.dao.*;
import classroster.dto.Student;
import classroster.ui.*;

public class ClassRosterController {
	private UserIO io = new UserIOConsoleImpl();
	private ClassRosterView view = new ClassRosterView();
	private ClassRosterDAO dao = new ClassRosterDAOFileImpl();
	
	private void createStudent() {
		view.displayBanner("CREATE STUDENT");
		Student student = view.getNewStudentInfo();
		dao.addStudent(student.getStudentID(), student);
		view.displayActionSuccess("Created Student.");
	}
	
	private void listStudents() {
		view.displayBanner("STUDENT ROSTER");
		view.displayStudentRoster(dao.getAllStudents());
		view.displayActionSuccess("Displayed Students.");
	}
	
	
	public void run(){
		boolean run = true;
		int input;
		
		
		while (run) {
			view.displayBanner("MAIN MENU");
			input = view.getMenuSelection();
			
			switch (input) {
			case 1:
				listStudents();
				break;
			case 2:
				createStudent();
				break;
			case 3:
				io.print("VIEW STUDENT");
				break;
			case 4:
				io.print("REMOVE STUDENT");
				break;
			case 5:
				io.print("EXITING...");
				run = false;
				break;
			default:
				io.print("Unknown Command.");
			}
		}
		io.close();
	}
}
