/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import classroster.dao.ClassRosterAuditDAO;
import classroster.dao.ClassRosterDAO;
import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;
import classroster.dto.Student.CompareStudent;


@Component
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
	
	private final ClassRosterDAO dao;
	private final ClassRosterAuditDAO auditLog;
	
	@Autowired
	public ClassRosterServiceLayerImpl(ClassRosterDAO dao, ClassRosterAuditDAO auditLog) {
		this.dao = dao;
		this.auditLog = auditLog;
	}

	@Override
	public void createStudent(Student student)
			throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
		String id = student.getStudentID();
		
		// check id
		if (dao.getStudent(id) != null)
			throw new ClassRosterDuplicateIdException("Student with ID [" + id + "] exists.");
		
		// validate data
		validateStudentData(student);
		
		// add student
		dao.addStudent(id, student);
		auditLog.writeAuditEntry("Student " + id + " CREATED");
	}

	@Override
	public List<Student> retrieveStudents() throws ClassRosterPersistenceException {
		return dao.getAllStudents();
	}

	@Override
	public Student retrieveStudent(String id) throws ClassRosterPersistenceException {
		return dao.getStudent(id);
	}

	@Override
	public Student removeStudent(String id) throws ClassRosterPersistenceException {
		Student stu = dao.removeStudent(id);
		auditLog.writeAuditEntry("Student " + id + " REMOVED");
		return stu;
		
	}
	
	private void validateStudentData(Student student) throws ClassRosterDataValidationExcepion{
		if (student.getFirstName() == null
				|| student.getFirstName().trim().length() == 0
				|| student.getLastName() == null
				|| student.getLastName().trim().length() == 0
				|| student.getCohort() == null
				|| student.getCohort().trim().length() == 0
				|| student.getStudentID() == null
				|| student.getStudentID().trim().length() == 0)
			
			throw new ClassRosterDataValidationExcepion("A field [ID, FirstName, LastName, Cohort] is missing.");
	}

	@Override
	public List<Student> retrieveStudentsByFirstName() throws ClassRosterPersistenceException {
		List<Student> students = retrieveStudents();
		return students.stream()
				.sorted(CompareStudent.FIRST_NAME)
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> retrieveStudentsByLastName() throws ClassRosterPersistenceException {
		List<Student> students = retrieveStudents();
		return students.stream()
				.sorted(CompareStudent.LAST_NAME)
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> retrieveStudentsByCohort() throws ClassRosterPersistenceException {
		List<Student> students = retrieveStudents();
		return students.stream()
				.sorted(CompareStudent.COHORT)
				.collect(Collectors.toList());
	}

}
