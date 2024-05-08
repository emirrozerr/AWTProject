package org.example.awtproject.dto;

import lombok.Data;
import org.example.awtproject.model.User;
@Data
public class WorkspaceDTO {
    private String name;
    private String description;
    private User user;
}
