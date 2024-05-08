package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class MedicationTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "taken_date")
    private LocalDate takenDate;

    private String note;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;
}