/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.service;

import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Assignment;
import classroster.dto.GradedAssignment;
import classroster.dto.Student;
import java.util.List;

public interface ClassRosterServiceLayer {

	void createStudent(Student student) throws ClassRosterDataValidationException,
			ClassRosterPersistenceException, ClassRosterDatabaseOverfillException;

	List<Student> retrieveStudents() throws ClassRosterPersistenceException;

	List<Student> retrieveStudentsByFirstName() throws ClassRosterPersistenceException;

	List<Student> retrieveStudentsByLastName() throws ClassRosterPersistenceException;

	List<Student> retrieveStudentsByCohort() throws ClassRosterPersistenceException;

	Student retrieveStudent(int studentId) throws ClassRosterPersistenceException;

	Student removeStudent(int studentId) throws ClassRosterPersistenceException;

	void createAssignment(Assignment assignment) throws ClassRosterPersistenceException, ClassRosterDatabaseOverfillException, ClassRosterDataValidationException;

	void createGradedAssignment(Assignment assignment, int receivedScore, Student student) throws ClassRosterPersistenceException, ClassRosterDataValidationException;

	List<Assignment> retrieveAssignments();

	List<GradedAssignment> retrieveGradedAssignments(Student student);

	Assignment retrieveAssignment(int assignmentID);

	GradedAssignment retrieveGradedAssignment(int studentID, int assignmentID);

	Assignment removeAssignment(int assignmentID);

	GradedAssignment removeGradedAssignment(int studentID, int assignmentID);

}