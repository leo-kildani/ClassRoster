package classroster.dao;

import java.util.*;

import classroster.dto.Student;

public interface ClassRosterDAO {
	
	/**
	 * Adds the given Student to the roster and associates it with the given student id.
	 * If there is already a student with the given student id it will return that student object.
	 * Otherwise it will return null.
	 * 
	 * @param studentID id with which student is to be associated with
	 * @param Student student to be added to the roster
	 * @return id if it exists, otherwise null
	 * @throws ClassRosterPersistenceException 
	 */
	Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException;

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
	 * @paramstudentID id to be looked for
	 * @return student associated with id, null otherwise
	 * @throws ClassRosterPersistenceException 
	 */
	Student getStudent(String studentID) throws ClassRosterPersistenceException;
	
	/**
	 * Removes student from roster associated with the given id.
	 * Return the student that has been removed or null if no student was found
	 * 
	 * @paramstudentID id of student to be removed
	 * @return Student if student found and removed, null otherwise
	 * @throws ClassRosterPersistenceException 
	 */
	Student removeStudent(String studentID) throws ClassRosterPersistenceException;
}
