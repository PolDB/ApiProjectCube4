package com.example.contactapp.demo.jpa;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDepartment")
    private Long idDepartment;
    private String department_name;

/*
    public Long getId_department() {
        return id_department;
    }

    public void setId_department(Long id_department) {
        this.id_department = id_department;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

 */
}
