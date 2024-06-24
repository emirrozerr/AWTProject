package org.example.awtproject.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class WorkspaceDTO {
    private String userId;
    private String name;
    private String description;
}
