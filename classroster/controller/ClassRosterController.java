package classroster.controller;

import classroster.dao.*;
import classroster.dto.Student;
import classroster.ui.*;

public class ClassRosterController {
	private UserIO io = new UserIOConsoleImpl();
	private ClassRosterView view = new ClassRosterView();
	private ClassRosterDAO dao = new ClassRosterDAOFileImpl();
	
	private void viewStudent() {
		view.displayBanner("VIEW STUDENT");
		Student student = dao.getStudent(view.getStudentID());
		view.displayStudentInfo(student);
		view.displayActionSuccess("View Student Performed.");
	}
	
	private void removeStudent() {
		view.displayBanner("REMOVE STUDENT");
		Student student = dao.removeStudent(view.getStudentID());
		view.displayStudentInfo(student);
		view.displayActionSuccess("Remove Student Performed.");
	}
	
	private void createStudent() {
		view.displayBanner("CREATE STUDENT");
		Student student = view.getNewStudentInfo();
		dao.addStudent(student.getStudentID(), student);
		view.displayActionSuccess("Create Student Perfomed.");
	}
	
	private void listStudents() {
		view.displayBanner("STUDENT ROSTER");
		view.displayStudentRoster(dao.getAllStudents());
		view.displayActionSuccess("Display Students Performed.");
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
				viewStudent();
				break;
			case 4:
				removeStudent();
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
