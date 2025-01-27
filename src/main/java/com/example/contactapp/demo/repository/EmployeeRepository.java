package com.example.contactapp.demo.repository;

import com.example.contactapp.demo.jpa.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //ajout pour la méthode pour trouver l'employé en fonction du departement
    List<Employee> findByDepartment_IdDepartment(Long departmentId);

    //ajout pour la méthode pour trouver l'employé en fonction du department
    List<Employee> findBysite_IdSite(Long departmentId);
}
