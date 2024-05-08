package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {
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
    @JoinColumn(name = "user_id")
    private User user;

}
