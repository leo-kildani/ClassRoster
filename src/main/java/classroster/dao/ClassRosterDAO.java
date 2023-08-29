package classroster.dao;

import java.util.*;

import classroster.dto.Assignment;
import classroster.dto.GradedAssignment;
import classroster.dto.Student;

public interface ClassRosterDAO {
	
	/**
	 * Adds the given Student to the roster and associates it with the given student id.
	 *
	 * @param student student to be added to the roster
	 * @throws ClassRosterPersistenceException
	 */
	void addStudent(Student student) throws ClassRosterPersistenceException;

	/**
	 * Returns a List of all students in the roster
	 * 
	 * @return List containing all the students
	 * @throws ClassRosterPersistenceException 
	 */
	List<Student> getAllStudents() throws ClassRosterPersistenceException;
	
	/**
	 * Returns the student associated with the given id.
	 * Return null if no such student exists
	 * 
	 * @param studentID id to be looked for
	 * @return student associated with id, null otherwise
	 * @throws ClassRosterPersistenceException 
	 */
	Student getStudent(Integer studentID) throws ClassRosterPersistenceException;
	
	/**
	 * Removes student from roster associated with the given id.
	 * Return the student that has been removed or null if no student was found
	 * 
	 * @param studentID id of student to be removed
	 * @return Student if student found and removed, null otherwise
	 * @throws ClassRosterPersistenceException 
	 */
	Student removeStudent(Integer studentID) throws ClassRosterPersistenceException;

	/**
	 * Returns an assignment with the given assignmentID, null otherwise.
	 *
	 * @param assignmentID
	 * @return Assignment if student found, null otherwise
	 */
	Assignment getAssignment(Integer assignmentID);

	/**
	 * Return a list of all assignments
	 *
	 * @return List of assignments
	 */
	List<Assignment> getAllAssignments();

	/**
	 * Add given assignment into database
	 * @param assignment
	 */
	void addAssignment(Assignment assignment) throws ClassRosterPersistenceException;

	/**
	 * Remove an assignment given an assignmentID
	 *
	 * @param assignmentID
	 * @return Assignment that has been removed, null otherwise
	 */
	Assignment removeAssignment(Integer assignmentID);

	GradedAssignment getStudentGradedAssignment(Integer studentID, Integer assignmentID);

	List<GradedAssignment> getStudentGradedAssignments(Integer studentID);

	void addGradedAssignment(Assignment assignment, Integer receivedScore, Integer studentID);

	GradedAssignment removeGradedAssignment(Integer studentID, Integer assignmentID);
}
