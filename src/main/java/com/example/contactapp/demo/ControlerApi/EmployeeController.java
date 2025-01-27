package com.example.contactapp.demo.ControlerApi;

import com.example.contactapp.demo.ServiceApi.EmployeeService;
import com.example.contactapp.demo.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://10.0.2.2")  // Autoriser CORS pour ce contrôleur
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = service.getAllEmployees();
        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    //@PreAuthorize("hasRole('ADMIN')") pour sécuriser l'accès à l'admin seulement
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDTO dto) {
        // Transformer le DTO en entité
        Employee employee = new Employee();
        employee.setFirstname(dto.getFirstname());
        employee.setMail(dto.getMail());
        employee.setName(dto.getName());
        employee.setPhone(dto.getPhone());

        Department department = new Department();
        department.setIdDepartment(dto.getIdDepartment());
        employee.setDepartment(department);

        Site site = new Site();
        site.setIdSite(dto.getIdSite());
        employee.setSite(site);

        // Appeler votre service pour persister les données
        Employee savedEmployee = service.saveEmployee(
                employee.getFirstname(),
                employee.getMail(),
                employee.getName(),
                employee.getPhone(),
                employee.getDepartment().getIdDepartment(),
                employee.getSite().getIdSite()
        );

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody EmployeeDTO dto) {
        // 1. Reconstituer l'entité Employee à partir du DTO
        Employee employee = new Employee();
        employee.setId(id);                      // on met l'ID provenant du PathVariable
        employee.setFirstname(dto.getFirstname());
        employee.setMail(dto.getMail());
        employee.setName(dto.getName());
        employee.setPhone(dto.getPhone());

        // 2. Reconstituer department et site
        Department department = new Department();
        department.setIdDepartment(dto.getIdDepartment());
        employee.setDepartment(department);

        Site site = new Site();
        site.setIdSite(dto.getIdSite());
        employee.setSite(site);

        // 3. Appeler la couche Service
        Employee updatedEmployee = service.updateEmployee(employee);

        // 4. Retourner la réponse au client
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {return service.deleteEmployee(id);  }
}

