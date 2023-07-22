package classroster.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "assignments")
public @Data class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assignment_id")
    @Setter(AccessLevel.NONE)
    private int assignmentID;
    private int maxScore;

    @Column(name = "name")
    private String name;

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "UTC")
    @Setter(AccessLevel.NONE)
    private Date dueDate;

    public Assignment(int maxScore, String name, LocalDate dueDate) {
        this.maxScore = maxScore;
        this.name = name;
        this.dueDate = Date.valueOf(dueDate);
    }

    public void setDueDate(int year, int month, int dayOfMonth) {
        dueDate = Date.valueOf(LocalDate.of(year, month, dayOfMonth));
    }

}


