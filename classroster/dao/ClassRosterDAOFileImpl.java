package classroster.dao;


import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;
import classroster.dto.Student;

public class ClassRosterDAOFileImpl implements ClassRosterDAO {
	private static final File ROSTER_FILE = new File("roster.txt");
	private static final String DELIMITER = "::";
	private final Map<String, Student> studentRoster = new HashMap<>();
	
	@Override
	public Student addStudent(String studentID,  Student student) throws ClassRosterPersistenceException {
		loadRoster();
		Student stu = studentRoster.put(studentID, student);
		writeRoster();
		return stu;
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
		loadRoster();
		return new ArrayList<>(studentRoster.values());
	}

	@Override
	public Student getStudent(String studentID) throws ClassRosterPersistenceException {
		loadRoster();
		return studentRoster.get(studentID);
	}

	@Override
	public Student removeStudent(String studentID) throws ClassRosterPersistenceException {
		loadRoster();
		Student stu = studentRoster.remove(studentID);
		writeRoster();
		return stu;
	}

	private Student unmarshalStudent(String studentInfo) {
		String[] studentTokens = studentInfo.split(DELIMITER);
		Student student = new Student(studentTokens[0]);
		student.setFirstName(studentTokens[1]);
		student.setLastName(studentTokens[2]);
		student.setCohort(studentTokens[3]);
		return student;
	}
	
	private String marshalStudent(Student stu) {
		return (stu.getStudentID() + DELIMITER + stu.getFirstName() + DELIMITER + stu.getLastName() + DELIMITER + stu.getCohort());
	}
	
	private void loadRoster() throws ClassRosterPersistenceException{
		try (Scanner in = new Scanner(new ByteArrayInputStream(Files.readAllBytes(ROSTER_FILE.toPath())))){
			while (in.hasNextLine()) {
				Student student = unmarshalStudent(in.nextLine());
				studentRoster.put(student.getStudentID(), student);
			}
		} catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not load roster data into memory.", e);
		}
	}
	
	private void writeRoster() throws ClassRosterPersistenceException{
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ROSTER_FILE)))){
			for (Student stu: getAllStudents()) {
				out.println(marshalStudent(stu));
			}
		} catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not save student data.", e);
		}
	}
}