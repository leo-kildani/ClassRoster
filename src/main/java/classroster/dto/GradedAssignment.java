package classroster.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "GradedAssignment")
@Table(name = "graded_assignments")
@NoArgsConstructor
public @Data class GradedAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
