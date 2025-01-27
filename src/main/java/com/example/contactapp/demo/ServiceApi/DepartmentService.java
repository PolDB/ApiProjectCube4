package com.example.contactapp.demo.ServiceApi;

import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    public String deleteDepartment(Long id) {
        repository.deleteById(id);
        return "Service removed || " + id;
    }

    public Department updateDepartment(Department department) {
        Department existingService = repository.findById(department.getIdDepartment()).orElse(null);

        if (existingService != null) {
            existingService.setDepartment_name(department.getDepartment_name());
            // Ajoute ici d'autres champs à mettre à jour si nécessaire
            return repository.save(existingService);
        } else {
            // Optionnel : gérer le cas où le département n'existe pas
            return null; // Ou lancer une exception selon le cas
        }
    }

}
