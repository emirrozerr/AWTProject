package org.example.awtproject.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private UUID userId;
    private String tokenType = "Bearer";

    public AuthResponseDTO(String accessToken, UUID userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }
}
