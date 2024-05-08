package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "medicine_name")
    private String medicineName;

    private String dose; // TODO Maybe Integer??

    private String instruction;

    private String frequency; // TODO find out a frequency logic and adjust the design

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
}
