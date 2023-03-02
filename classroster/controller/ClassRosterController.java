package classroster.controller;

import classroster.api.ClassRosterDataValidationExcepion;
import classroster.api.ClassRosterDuplicateIdException;
import classroster.api.ClassRosterServiceLayer;
import classroster.dao.*;
import classroster.dto.Student;
import classroster.ui.ClassRosterView;

public class ClassRosterController {
	private ClassRosterView view;
	private ClassRosterServiceLayer api;
	
	public ClassRosterController(ClassRosterView view, ClassRosterServiceLayer api) {
		this.view = view;
		this.api = api;
	}
	
	public void exit() {
		view.displayExitMessage();
	}
	
	private void viewStudent() throws ClassRosterPersistenceException {
		view.displayBanner("VIEW STUDENT");
		Student student = api.retrieveStudent(view.getStudentID());
		view.displayStudentInfo(student);
		view.displayActionSuccess("View Student Performed.");
	}
	
	private void removeStudent() throws ClassRosterPersistenceException {
		view.displayBanner("REMOVE STUDENT");
		Student student = api.removeStudent(view.getStudentID());
		view.displayRemoveStudent(student);
		view.displayActionSuccess("Remove Student Performed.");
	}
	
	private void createStudent() throws ClassRosterPersistenceException {
		view.displayBanner("CREATE STUDENT");
		
		do {
			Student student = view.getNewStudentInfo();
			try {
				api.createStudent(student);
				view.displayActionSuccess("Create Student Perfomed.");
				break;
			} catch (ClassRosterDuplicateIdException | ClassRosterDataValidationExcepion e) {
			view.displayErrorMessage(e.getMessage());
			}
		} while (true);
	}
	
	private void listStudents() throws ClassRosterPersistenceException {
		view.displayBanner("STUDENT ROSTER");
		view.displayStudentRoster(api.retrieveStudents());
		view.displayActionSuccess("Display Students Performed.");
	}
	
	
	public void run(){
		boolean run = true;
		int input;
		
		while (run) {
			view.displayBanner("MAIN MENU");
			input = view.getMenuSelection();
			
			try {
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
						view.displayBanner("EXITING");
						run = false;
						break;
				}
			} catch (Exception e) {
				view.displayErrorMessage(e.getMessage());
			}
		}
	exit();
	}
}
