package classroster.controller;

import classroster.ui.*;

public class ClassRosterController {
	UserIO io = new UserIOConsoleImpl();
	ClassRosterView view = new ClassRosterView();
	
	public void run(){
		boolean run = true;
		int input;
		
		
		while (run) {
			input = view.getMenuSelection();
			
			switch (input) {
			case 1:
				io.print("LIST STUDENT");
				break;
			case 2:
				io.print("CREATE STUDENT");
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
