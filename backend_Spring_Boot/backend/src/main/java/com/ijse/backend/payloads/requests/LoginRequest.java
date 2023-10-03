package com.ijse.backend.payloads.requests;

import lombok.Getter;
import lombok.Setter;

//lombok getters and setters
@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
