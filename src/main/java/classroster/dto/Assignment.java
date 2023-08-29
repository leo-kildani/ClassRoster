package classroster.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity(name = "Assignment")
@Table(name = "assignments")
@NoArgsConstructor
public @Data class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private int assignmentID;

    @Column(name = "max_score")
    private int maxScore;

    @Column(name = "title")
    private String title;

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "UTC")
    private Date dueDate;

    public Assignment(int maxScore, String title, LocalDate dueDate) {
        this.maxScore = maxScore;
        this.title = title;
        this.dueDate = Date.valueOf(dueDate);
    }

    public void setDueDate(int year, int month, int dayOfMonth) {
        dueDate = Date.valueOf(LocalDate.of(year, month, dayOfMonth));
    }

    public String[] toArray(){
        return new String[] {
                String.valueOf(assignmentID),
                String.valueOf(maxScore),
                dueDate.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),
                title
        };
    }

}


