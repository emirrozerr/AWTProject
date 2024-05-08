package org.example.awtproject.dto;

import lombok.Data;
import org.example.awtproject.model.Workspace;

@Data
public class ProjectDTO {
    private String name;
    private String description;
    private Workspace workspace;
}
