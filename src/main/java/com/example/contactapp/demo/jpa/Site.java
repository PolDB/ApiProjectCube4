package com.example.contactapp.demo.jpa;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSite")
    private Long idSite;
    private String city;
/*
    public long getId_site() {
        return id_site;
    }

    public void setId_site(Long id_site) {
        this.id_site = id_site;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

 */
}
