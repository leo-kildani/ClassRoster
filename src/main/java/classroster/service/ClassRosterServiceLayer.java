/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.service;

import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;
import java.util.List;

public interface ClassRosterServiceLayer {

		public void createStudent(Student student) throws ClassRosterDuplicateIdException, 
											ClassRosterDataValidationException,
													ClassRosterPersistenceException;
		
		public List<Student> retrieveStudents() throws ClassRosterPersistenceException;
		
		public List<Student> retrieveStudentsByFirstName() throws ClassRosterPersistenceException;
		
		public List<Student> retrieveStudentsByLastName() throws ClassRosterPersistenceException;
		
		public List<Student> retrieveStudentsByCohort() throws ClassRosterPersistenceException;
		
		public Student retrieveStudent(String id) throws ClassRosterPersistenceException;
		
		public Student removeStudent(String id) throws ClassRosterPersistenceException;
}
