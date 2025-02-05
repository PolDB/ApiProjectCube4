package com.example.contactapp.demo.jpa;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AuthResponse implements Serializable {
    private String username;
    private String password;

    public AuthResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
