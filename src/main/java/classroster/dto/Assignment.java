package classroster.dto;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
    @Setter(AccessLevel.NONE)
    private Date dueDate;

    @Getter(AccessLevel.NONE)
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

    public Assignment(int maxScore, String name, LocalDate dueDate) {
        this.maxScore = maxScore;
        this.name = name;
        this.dueDate = Date.valueOf(dueDate);
    }

    public void setDueDate(LocalDate date) {
        dueDate = Date.valueOf(date);
    }

}


