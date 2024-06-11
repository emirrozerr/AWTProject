package org.example.awtproject.dto;

import lombok.Data;
@Data
public class TaskDTO {
    private Integer projectId;
    private String title;
    private String description;
    private String dueDate; //Receive the date as a string from json
    private String priority; // TODO Connect it to a certain list of Priority class
    private String status;
}
