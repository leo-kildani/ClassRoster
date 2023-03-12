/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.service;

import java.util.List;

import classroster.dao.ClassRosterAuditDAO;
import classroster.dao.ClassRosterDAO;
import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
	
	private final ClassRosterDAO dao;
	private final ClassRosterAuditDAO auditLog;
	
	public ClassRosterServiceLayerImpl(ClassRosterDAO dao, ClassRosterAuditDAO auditLog) {
		this.dao = dao;
		this.auditLog = auditLog;
	}

	@Override
	public void createStudent(Student student)
			throws ClassRosterDuplicateIdException, ClassRosterDataValidationExcepion, ClassRosterPersistenceException {
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
				|| student.getCohort().trim().length() == 0)
			
			throw new ClassRosterDataValidationExcepion("A field [FirstName, LastName, Cohort] is missing.");
	}

}
