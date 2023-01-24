package classroster.ui;

public class ClassRosterView {
	private UserIO io = new UserIOConsoleImpl();
	
	public int getMenuSelection() {
		io.print("MAIN MENU\n==========");
		io.print("1. List Student IDs");
		io.print("2. Create New Student");
		io.print("3. View A Student");
		io.print("4. Remove A Student");
		io.print("5. Exit");
		
		return io.readInt("Please select from the above choices (1-5): ", 1, 5);
	}
}
