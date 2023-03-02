/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.api;

import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;
import java.util.List;

public interface ClassRosterServiceLayer {

		public void createStudent(Student student) throws ClassRosterDuplicateIdException, 
											ClassRosterDataValidationExcepion,
													ClassRosterPersistenceException;
		
		public List<Student> retrieveStudents() throws ClassRosterPersistenceException;
		
		public Student retrieveStudent(String id) throws ClassRosterPersistenceException;
		
		public Student removeStudent(String id) throws ClassRosterPersistenceException;
}
