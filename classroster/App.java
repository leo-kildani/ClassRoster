package classroster;

import classroster.api.ClassRosterServiceLayer;
import classroster.api.ClassRosterServiceLayerImpl;
import classroster.controller.ClassRosterController;
import classroster.dao.ClassRosterDAO;
import classroster.dao.ClassRosterDAOFileImpl;
import classroster.ui.*;

public class App {
	public static void main(String[] args) {
		UserIO io = new UserIOConsoleImpl();
		ClassRosterView view = new ClassRosterView(io);
		ClassRosterDAO dao = new ClassRosterDAOFileImpl();
		ClassRosterServiceLayer api = new ClassRosterServiceLayerImpl(dao);
		ClassRosterController controller = new ClassRosterController(view, api);
		controller.run();
	}
}
