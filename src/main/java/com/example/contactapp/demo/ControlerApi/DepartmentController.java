package com.example.contactapp.demo.ControlerApi;

import com.example.contactapp.demo.ServiceApi.DepartmentService;
import com.example.contactapp.demo.ServiceApi.EmployeeService;
import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.jpa.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://10.0.2.2")  // Autoriser CORS pour ce contrôleur
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService service;

    @GetMapping
    public List<Department> getAllDepartments() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return service.getDepartmentById(id);
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        return service.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        // Met à jour le département en utilisant l'ID
        department.setIdDepartment(id); // S'assurer que l'ID est dans l'objet à mettre à jour
        return service.updateDepartment(department);
    }
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        return service.deleteDepartment(id);
    }


    //ajout de la méthode pour récupérer des employés en fonction du service
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long id) {
        return employeeService.getEmployeesByDepartment(id);
    }

}