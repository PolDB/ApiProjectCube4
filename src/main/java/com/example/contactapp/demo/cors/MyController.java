package com.example.contactapp.demo.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "http://10.0.2.2")  // Autoriser CORS pour ce contrôleur
@RequestMapping("/api")
public class MyController {

    @GetMapping("/employee")
    public String getExample() {
        return "Hello, world!";
    }
}