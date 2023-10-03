package com.ijse.backend.payloads.responses;

import lombok.Getter;
import lombok.Setter;

//Lombok getters and setters
@Getter
@Setter
public class JwtResponse {
    private String token;
    private Long id;
    private String username;
    private String email;
    private String role;

    public JwtResponse(String token, Long id, String username, String email, String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
