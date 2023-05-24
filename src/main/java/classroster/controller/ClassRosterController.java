package classroster.controller;

import classroster.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import classroster.dao.*;
import classroster.dto.Student;
import classroster.service.ClassRosterDataValidationException;
import classroster.ui.ClassRosterView;

@Component
public class ClassRosterController {
	private final ClassRosterView view;
	private final ClassRosterServiceLayer service;
	
	@Autowired
	public ClassRosterController(ClassRosterView view, ClassRosterServiceLayer service) {
		this.view = view;
		this.service = service;
	}
	
	private void exit() {
		view.displayExitMessage();
	}
	
	private void viewStudent() throws ClassRosterPersistenceException {
		view.displayBanner("VIEW STUDENT");
		Student student = service.retrieveStudent(view.getStudentID());
		view.displayStudentInfo(student);
		view.displayActionSuccess("View Student Performed.");
	}
	
	private void removeStudent() throws ClassRosterPersistenceException {
		view.displayBanner("REMOVE STUDENT");
		Student student = service.removeStudent(view.getStudentID());
		view.displayRemoveStudent(student);
		view.displayActionSuccess("Remove Student Performed.");
	}
	
	private void createStudent() throws ClassRosterPersistenceException {
		view.displayBanner("CREATE STUDENT");
		
		do {
			Student student = view.getNewStudentInfo();
			try {
				service.createStudent(student);
				view.displayActionSuccess("Create Student Performed.");
				break;
			} catch (ClassRosterDataValidationException | ClassRosterDatabaseOverfillException e) {
			view.displayErrorMessage(e.getMessage());
			}
		} while (true);
	}
	
	private void listStudents() throws ClassRosterPersistenceException {
		int input = view.getListSelection();
		view.displayBanner("Student List");
		switch (input) {
			case 1 -> view.displayStudentRoster(service.retrieveStudentsByFirstName());
			case 2 -> view.displayStudentRoster(service.retrieveStudentsByLastName());
			case 3 -> view.displayStudentRoster(service.retrieveStudentsByCohort());
		}
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
					case 1 -> listStudents();
					case 2 -> createStudent();
					case 3 -> viewStudent();
					case 4 -> removeStudent();
					case 5 -> {
						view.displayBanner("EXITING");
						run = false;
					}
				}
			} catch (Exception e) {
				view.displayErrorMessage(e.getMessage());
			}
		}
	exit();
	}
}
