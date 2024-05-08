package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
