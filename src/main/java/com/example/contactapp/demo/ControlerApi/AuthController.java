package com.example.contactapp.demo.ControlerApi;

import com.example.contactapp.demo.jpa.AuthResponse;
import com.example.contactapp.demo.jpa.LoginRequest;
import com.example.contactapp.demo.jpa.User;
import com.example.contactapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository service; // Repository pour récupérer l'utilisateur

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = service.findByUsername(request.getUsername());

        if (user == null || !BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
        }

        AuthResponse response = new AuthResponse(user.getUsername(), user.getRole());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
