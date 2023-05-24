package classroster.dao;

import java.util.*;

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
}
