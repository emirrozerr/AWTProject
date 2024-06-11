package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Column(updatable = false, name= "created_at_utc")
    @CreationTimestamp
    LocalDateTime createdAtUTC;

    @Column(updatable = false, name= "updated_at_utc")
    @UpdateTimestamp
    LocalDateTime updatedAtUTC;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    private String priority; // TODO Connect it to a certain list of Priority class

    private String status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "task_labels",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<Label> labels;

}
