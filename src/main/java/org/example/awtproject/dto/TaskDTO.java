package org.example.awtproject.dto;

import lombok.Data;
import org.example.awtproject.model.Project;

import java.util.Date;
@Data
public class TaskDTO {
    private String title;
    private String description;
    private String dueDate; //Receive the date as a string from json
    private String priority; // TODO Connect it to a certain list of Priority class
    private String status;
    private Project project;
}
