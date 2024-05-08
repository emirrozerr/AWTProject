package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class HabitCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;
}
