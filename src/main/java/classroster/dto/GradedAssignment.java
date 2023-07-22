package classroster.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import java.sql.Date;

@Entity
@Table(name = "graded_assignments")
public @Data class GradedAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "graded_assignment_id")
    @Setter(AccessLevel.NONE)
    private int gradedAssignmentID;

    @Column(name = "assignment_id")
    @Setter(AccessLevel.NONE)
    private int assignmentID;

    @Column(name = "max_score")
    @Setter(AccessLevel.NONE)
    private int maxScore;

    @Column(name = "name")
    private String name;

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "UTC")
    @Setter(AccessLevel.NONE)
    private Date dueDate;

    @Column(name = "student_id")
    @Setter(AccessLevel.NONE)
    private int studentID;

    @Column(name = "received_score")
    private double receivedScore;

    public GradedAssignment(Assignment assignment, int studentID, double receivedScore) {
        this.assignmentID = assignment.getAssignmentID();
        this.dueDate = assignment.getDueDate();
        this.name = assignment.getName();
        this.maxScore = assignment.getMaxScore();
        this.studentID = studentID;
        this.receivedScore = receivedScore;
    }
}
