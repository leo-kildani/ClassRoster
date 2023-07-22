/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public ClassRosterServiceLayerImpl(@Qualifier("classRosterDAODatabaseImpl") ClassRosterDAO dao, ClassRosterAuditDAO auditLog) {
		this.dao = dao;
		this.auditLog = auditLog;
	}

	@Override
	public void createStudent(Student student) throws ClassRosterDataValidationException,
			ClassRosterPersistenceException, ClassRosterDatabaseOverfillException {

		int id = student.getStudentID();

		validateDbSize();

		// assure no duplicate id
		while (dao.getStudent(id) != null)
			student.setStudentID(Student.generateID());
		
		// validate data
		validateStudentData(student);
		
		// add student
		dao.addStudent(student);
		auditLog.writeAuditEntry("Student " + id + " CREATED");
	}

	@Override
	public List<Student> retrieveStudents() throws ClassRosterPersistenceException {
		return dao.getAllStudents();
	}

	@Override
	public Student retrieveStudent(int studentId) throws ClassRosterPersistenceException {
		return dao.getStudent(studentId);
	}

	@Override
	public Student removeStudent(int studentId) throws ClassRosterPersistenceException {
		Student stu = dao.removeStudent(studentId);
		auditLog.writeAuditEntry("Student " + studentId + " REMOVED");
		return stu;
	}
	
	private void validateStudentData(Student student) throws ClassRosterDataValidationException {
		if (student.getFirstName() == null
				|| student.getFirstName().trim().length() == 0
				|| student.getLastName() == null
				|| student.getLastName().trim().length() == 0
				|| student.getCohort() == null
				|| student.getCohort().trim().length() == 0
		)
			
			throw new ClassRosterDataValidationException("A field [ID, FirstName, LastName, Cohort] is missing.");
	}

	// Max Database Size (8999)
	private void validateDbSize() throws ClassRosterDatabaseOverfillException, ClassRosterPersistenceException {
		int size = dao.getAllStudents().size();
		if (size > 8999)
			throw new ClassRosterDatabaseOverfillException("Maximum Database Size Exceeded.");
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
