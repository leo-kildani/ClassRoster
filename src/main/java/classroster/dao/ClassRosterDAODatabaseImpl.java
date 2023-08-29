package classroster.dao;

import classroster.dto.Assignment;
import classroster.dto.GradedAssignment;
import classroster.dto.Student;
import classroster.repository.AssignmentRepository;
import classroster.repository.GradedAssignmentRepository;
import classroster.repository.StudentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassRosterDAODatabaseImpl implements ClassRosterDAO{

    private final StudentRepository studentRepo;

    private final AssignmentRepository assignmentRepo;

    private final GradedAssignmentRepository gradedAssignmentRepo;

    @Autowired
    public ClassRosterDAODatabaseImpl(StudentRepository repo, AssignmentRepository assignmentRepo, GradedAssignmentRepository gradedAssignmentRepo) {
        this.studentRepo = repo;
        this.assignmentRepo = assignmentRepo;
        this.gradedAssignmentRepo = gradedAssignmentRepo;
    }

    @Override
    @Transactional
    public void addStudent(Student student) throws ClassRosterPersistenceException {
        try {
            studentRepo.save(student);
        } catch (EntityExistsException e) {
            throw new ClassRosterPersistenceException("Could Not Persist Information");
        }
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudent(Integer studentID) throws ClassRosterPersistenceException {
        return studentRepo.findById(studentID).orElse(null);
    }

    @Override
    @Transactional
    public Student removeStudent(Integer studentID) throws ClassRosterPersistenceException {
        Student toRemove = getStudent(studentID);
        if (toRemove != null)
            studentRepo.deleteById(studentID);
        return toRemove;
    }

    @Override
    public Assignment getAssignment(Integer assignmentID) {
        return assignmentRepo.findById(assignmentID).orElse(null);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    @Override
    public void addAssignment(Assignment assignment) throws ClassRosterPersistenceException {
        try {
            assignmentRepo.save(assignment);
        } catch (EntityExistsException e) {
            throw new ClassRosterPersistenceException("Could not persist Information");
        }
    }

    @Override
    public Assignment removeAssignment(Integer assignmentID) {
        Assignment toRemove = getAssignment(assignmentID);
        if (toRemove != null) {
            assignmentRepo.deleteById(assignmentID);
            gradedAssignmentRepo.deleteAllByAssignmentID(assignmentID);
        }
        return toRemove;
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
}
