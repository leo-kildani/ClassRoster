package classroster.dao;

import classroster.dto.Student;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassRosterDAODatabaseImpl implements ClassRosterDAO{

    private final EntityManager entityManager;

    @Autowired
    public ClassRosterDAODatabaseImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addStudent(Student student) throws ClassRosterPersistenceException {
        try {
            entityManager.persist(student);
        } catch (EntityExistsException | IllegalArgumentException e) {
            throw new ClassRosterPersistenceException("Could Not Persist Information");
        }
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student getStudent(Integer studentID) throws ClassRosterPersistenceException {
        return entityManager.find(Student.class, studentID);
    }

    @Override
    @Transactional
    public Student removeStudent(Integer studentID) throws ClassRosterPersistenceException {
        Student studentToRemove = getStudent(studentID);
        if (studentToRemove != null)
            entityManager.remove(studentToRemove);
        return studentToRemove;
    }
}
