package com.example.contactapp.demo.ServiceApi;

import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.jpa.Employee;
import com.example.contactapp.demo.jpa.Site;
import com.example.contactapp.demo.repository.DepartmentRepository;
import com.example.contactapp.demo.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.contactapp.demo.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SiteRepository siteRepository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Employee not found"));
    }

    public Employee saveEmployee(String firstname, String mail, String name, String phone, Long idDepartment, Long idSite) {

        Department department = departmentRepository.findById(idDepartment)
                .orElseThrow(() -> new EntityNotFoundException("Department with ID " + idDepartment + " not found"));

        Site site = siteRepository.findById(idSite)
                .orElseThrow(() -> new EntityNotFoundException("Site with ID " + idSite + " not found"));


        Employee employee = new Employee();
        employee.setFirstname(firstname);
        employee.setMail(mail);
        employee.setName(name);
        employee.setPhone(phone);
        employee.setDepartment(department);
        employee.setSite(site);

        return repository.save(employee);
    }

    public String deleteEmployee(Long id) {
        repository.deleteById(id);
        return "Employee removed || " + id;
    }

    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);

    }

//méthode pour mettre à jour un employé en fonction du service

    public List<Employee> getEmployeesByDepartment(Long departmentId) {

        // (Optionnel) Vérifier que le département existe
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department with ID " + departmentId + " not found"));

        // Récupérer la liste des employés via la méthode du repository
        // Voir la section "2. Méthode dans EmployeeRepository" ci-dessous
        return repository.findByDepartment_IdDepartment(departmentId);
    }

//méthode pour mettre à jour un employé en fonction du site

    public List<Employee> getEmployeesBySite(Long siteId) {

        // (Optionnel) Vérifier que le département existe
        siteRepository.findById(siteId)
                .orElseThrow(() -> new EntityNotFoundException("Department with ID " + siteId + " not found"));

        // Récupérer la liste des employés via la méthode du repository
        // Voir la section "2. Méthode dans EmployeeRepository" ci-dessous
        return repository.findBysite_IdSite(siteId);
    }
}
