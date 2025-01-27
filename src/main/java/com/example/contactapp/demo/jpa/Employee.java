package com.example.contactapp.demo.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String firstname;
    private String phone;
    private String mail;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idDepartment") // Indique la colonne de clé étrangère
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignorer les champs proxy
    private Department department; // Entité cible

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idSite")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Site site;

    // Les getters et setters sont générés par Lombok grâce à l'annotation @Data.
    // Il n'est pas nécessaire de les redéfinir ici.

    // Si tu veux explicitement exposer des méthodes pour l'id du service et du site, fais-le comme ceci :
/*
    public Long getDepartmentId() {
        return department != null ? department.getId_department() : null;
    }

    public Long getSiteId() {
        return site != null ? site.getId_site() : null;
    }

    public void setDepartmentId(Long id_department) {
        if (this.department == null) {
            this.department = new Department();  // Crée une nouvelle instance si nécessaire
        }
        this.department.setId_department(id_department);  // Affecte l'id au département
    }

    public void setSiteId(Long id_site) {
        if (this.site == null) {
            this.site = new Site();  // Crée une nouvelle instance si nécessaire
        }
        this.site.setId_site(id_site);  // Affecte l'id au site
    }

 */


}
