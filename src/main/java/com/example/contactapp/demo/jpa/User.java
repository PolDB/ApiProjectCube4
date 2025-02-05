package com.example.contactapp.demo.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String role;

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());

    }

}

