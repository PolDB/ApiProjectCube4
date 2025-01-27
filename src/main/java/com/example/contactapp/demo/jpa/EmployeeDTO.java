package com.example.contactapp.demo.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;
import lombok.Data;

@Entity
@Data
public class EmployeeDTO {
    @Id
    private Long id;
    private String firstname;
    private String mail;
    private String name;
    private String phone;
    private Long idDepartment;
    private Long idSite;

    // getters, setters
}
