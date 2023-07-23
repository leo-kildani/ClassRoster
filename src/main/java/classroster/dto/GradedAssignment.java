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

    @Column(name = "student_id")
    @Setter(AccessLevel.NONE)
    private int studentID;

    @Column(name = "received_score")
    private double receivedScore;

    public GradedAssignment(int assignmentID, int studentID, double receivedScore) {
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.receivedScore = receivedScore;
    }
}
