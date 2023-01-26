package classroster.dao;

import java.util.*;
import classroster.dto.Student;

public class ClassRosterDAOFileImpl implements ClassRosterDAO {
	private final Map<String, Student> studentRoster = new HashMap<>();
	
	@Override
	public Student addStudent(String studentID,  Student student) {
		return studentRoster.put(studentID, student);
	}

	@Override
	public List<Student> getAllStudents() {
		return new ArrayList<>(studentRoster.values());
	}

	@Override
	public Student getStudent(String studentID) {
		return studentRoster.get(studentID);
	}

	@Override
	public Student removeStudent(String studentID) {
		// TODO Auto-generated method stub
		return null;
	}

}
