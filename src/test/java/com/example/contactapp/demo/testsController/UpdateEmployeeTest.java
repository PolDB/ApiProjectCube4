package com.example.contactapp.demo.testsController;

import com.example.contactapp.demo.ControlerApi.EmployeeController;
import com.example.contactapp.demo.ServiceApi.EmployeeService;
import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.jpa.Employee;
import com.example.contactapp.demo.jpa.EmployeeDTO;
import com.example.contactapp.demo.jpa.Site;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put; // Important
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // Important
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class UpdateEmployeeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service; // On mocke la couche service

    @Test
    void testUpdateEmployee() throws Exception {
        // GIVEN
        // Création d'un DTO d'entrée
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFirstname("John");
        dto.setName("Doe");
        dto.setMail("john.doe@example.com");
        dto.setPhone("0123456789");
        dto.setIdDepartment(2L);
        dto.setIdSite(3L);

        // Création de l'Employee simulé renvoyé par la couche service
        Employee returnedEmployee = new Employee();
        returnedEmployee.setId(1L);
        returnedEmployee.setFirstname("John");
        returnedEmployee.setName("Doe");
        returnedEmployee.setMail("john.doe@example.com");
        returnedEmployee.setPhone("0123456789");

        Department department = new Department();
        department.setIdDepartment(2L);
        returnedEmployee.setDepartment(department);

        Site site = new Site();
        site.setIdSite(3L);
        returnedEmployee.setSite(site);

        // Configuration du mock : quand on appelle updateEmployee, on renvoie returnedEmployee
        when(service.updateEmployee(any(Employee.class))).thenReturn(returnedEmployee);

        // WHEN + THEN
        // On effectue la requête PUT sur /employees/1
        mockMvc.perform(
                        put("/api/employees/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(dto))
                )
                .andExpect(status().isOk())                               // Vérifie le code HTTP 200
                .andExpect(jsonPath("$.id").value(1L))                   // Vérifie le champ "id"
                .andExpect(jsonPath("$.firstname").value("John"))        // Vérifie le champ "firstname"
                .andExpect(jsonPath("$.name").value("Doe"))              // Vérifie le champ "name"
                .andExpect(jsonPath("$.mail").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phone").value("0123456789"))
                .andExpect(jsonPath("$.department.idDepartment").value(2L))
                .andExpect(jsonPath("$.site.idSite").value(3L));

        // Vérifie que le service a bien été appelé avec les bons arguments
        verify(service).updateEmployee(argThat(employee ->
                employee.getId().equals(1L) &&
                        employee.getFirstname().equals("John") &&
                        employee.getName().equals("Doe") &&
                        employee.getMail().equals("john.doe@example.com") &&
                        employee.getPhone().equals("0123456789") &&
                        employee.getDepartment().getIdDepartment().equals(2L) &&
                        employee.getSite().getIdSite().equals(3L)
        ));
    }

    /**
     * Méthode utilitaire pour convertir un objet en JSON (String).
     */
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
