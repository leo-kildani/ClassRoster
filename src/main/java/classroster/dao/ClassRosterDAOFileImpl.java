package classroster.dao;


import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;

import classroster.dto.Assignment;
import classroster.dto.GradedAssignment;
import org.springframework.stereotype.Component;

import classroster.dto.Student;

@Component
public class ClassRosterDAOFileImpl implements ClassRosterDAO {
	private final File roster_file;
	private static final String DELIMITER = "::";
	private final Map<Integer, Student> studentRoster = new HashMap<>();
	
	public ClassRosterDAOFileImpl() {
		roster_file = new File("src\\main\\resources\\roster.txt");
	}
	
	public ClassRosterDAOFileImpl(String rosterFile) {
		roster_file = new File(rosterFile);
	}
	
	@Override
	public void addStudent(Student student) throws ClassRosterPersistenceException {
		loadRoster();
		Student stu = studentRoster.put(student.getStudentID(), student);
		writeRoster();
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
		loadRoster();
		return new ArrayList<>(studentRoster.values());
	}

	@Override
	public Student getStudent(Integer studentID) throws ClassRosterPersistenceException {
		loadRoster();
		return studentRoster.get(studentID);
	}

	@Override
	public Student removeStudent(Integer studentID) throws ClassRosterPersistenceException {
		loadRoster();
		Student stu = studentRoster.remove(studentID);
		writeRoster();
		return stu;
	}

	@Override
	public Assignment getAssignment(Integer assignmentID) {
		return null;
	}

	@Override
	public List<Assignment> getAllAssignments() {
		return null;
	}

	@Override
	public void addAssignment(Assignment assignment) throws ClassRosterPersistenceException {

	}

	@Override
	public Assignment removeAssignment(Integer assignmentID) {
		return null;
	}

	@Override
	public GradedAssignment getStudentGradedAssignment(Integer studentID, Integer assignmentID) {
		return null;
	}

	@Override
	public List<GradedAssignment> getStudentGradedAssignments(Integer studentID) {
		return null;
	}

	@Override
	public void addGradedAssignment(Assignment assignment, Integer receivedScore, Integer studentID) {

	}

	@Override
	public GradedAssignment removeGradedAssignment(Integer studentID, Integer assignmentID) {
		return null;
	}

	private Student unmarshalStudent(String studentInfo) {
		String[] studentTokens = studentInfo.split(DELIMITER);
		Student student = new Student(Integer.parseInt(studentTokens[0]));
		student.setFirstName(studentTokens[1]);
		student.setLastName(studentTokens[2]);
		student.setCohort(studentTokens[3]);
		return student;
	}
	
	private String marshalStudent(Student stu) {
		return (stu.getStudentID() + DELIMITER + stu.getFirstName() + DELIMITER + stu.getLastName() + DELIMITER + stu.getCohort());
	}
	
	private void loadRoster() throws ClassRosterPersistenceException{
		try (Scanner in = new Scanner(new ByteArrayInputStream(Files.readAllBytes(roster_file.toPath())))){
			while (in.hasNextLine()) {
				Student student = unmarshalStudent(in.nextLine());
				studentRoster.put(student.getStudentID(), student);
			}
		} catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not load roster data into memory.", e);
		}
	}
	
	private void writeRoster() throws ClassRosterPersistenceException{
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(roster_file)))){
			for (Student stu: getAllStudents()) {
				out.println(marshalStudent(stu));
			}
		} catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not save student data.", e);
		}
	}
}