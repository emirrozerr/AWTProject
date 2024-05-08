package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Column(updatable = false, name= "created_at_utc")
    @CreationTimestamp
    LocalDateTime createdAtUTC;

    @Column(updatable = false, name= "updated_at_utc")
    @UpdateTimestamp
    LocalDateTime updatedAtUTC;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    //TODO Instead of this, use @RequiredArgsConstructor from lombok
    public Project(String name, String description, Workspace workspace) {
        this.name = name;
        this.description = description;
        this.workspace = workspace;
    }
}
