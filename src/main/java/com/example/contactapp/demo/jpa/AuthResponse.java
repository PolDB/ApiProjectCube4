package com.example.contactapp.demo.jpa;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AuthResponse implements Serializable {
    private String username;
    private String role;

    public AuthResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }

}
