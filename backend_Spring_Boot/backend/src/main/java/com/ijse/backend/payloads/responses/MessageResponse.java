package com.ijse.backend.payloads.responses;

import lombok.Getter;
import lombok.Setter;

//This class convert all responses into json format

//Lombok getters and setters
@Getter
@Setter
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
