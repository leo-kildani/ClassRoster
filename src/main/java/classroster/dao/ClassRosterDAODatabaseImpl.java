package classroster.dao;

import classroster.dto.Student;
import classroster.repository.StudentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassRosterDAODatabaseImpl implements ClassRosterDAO{

    private final StudentRepository studentRepo;

    @Autowired
    public ClassRosterDAODatabaseImpl(StudentRepository repo) {
        this.studentRepo = repo;
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
        return studentRepo.findById(studentID.toString()).orElse(null);
    }

    @Override
    @Transactional
    public Student removeStudent(Integer studentID) throws ClassRosterPersistenceException {
        Student toRemove = getStudent(studentID);
        if (toRemove != null)
            studentRepo.deleteById(studentID.toString());
        return toRemove;
    }
}
