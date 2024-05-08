package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    private String frequency; // TODO Get it with a proper data type

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "habit", targetEntity = HabitCompletion.class, orphanRemoval = true , cascade = CascadeType.ALL)
    private List<HabitCompletion> habitCompletionList;
}
