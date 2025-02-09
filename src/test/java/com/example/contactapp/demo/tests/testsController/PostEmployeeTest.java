package com.example.contactapp.demo.tests.testsController;

import com.example.contactapp.demo.ControlerApi.EmployeeController;
import com.example.contactapp.demo.ServiceApi.EmployeeService;
import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.jpa.Employee;
import com.example.contactapp.demo.jpa.EmployeeDTO;
import com.example.contactapp.demo.jpa.Site;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
public class PostEmployeeTest {

    @Autowired
    private MockMvc mockMvc;

    // On moque le service utilisé par le contrôleur
    @MockBean
    private EmployeeService employeeService;

    @Test
    @DisplayName("Test de la création d'un employé via le contrôleur")
    void testSaveEmployee() throws Exception {
        // GIVEN
        // Préparation du DTO en JSON
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstname("John");
        employeeDTO.setMail("john.doe@example.com");
        employeeDTO.setName("Doe");
        employeeDTO.setPhone("123456789");
        employeeDTO.setIdDepartment(1L);
        employeeDTO.setIdSite(2L);

        // Transformation du DTO en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String dtoAsJson = objectMapper.writeValueAsString(employeeDTO);

        // Préparation de l'employé simulé qui sera retourné par le service
        Employee savedEmployee = new Employee();
        savedEmployee.setId(100L);
        savedEmployee.setFirstname("John");
        savedEmployee.setMail("john.doe@example.com");
        savedEmployee.setName("Doe");
        savedEmployee.setPhone("123456789");

        Department department = new Department();
        department.setIdDepartment(1L);
        savedEmployee.setDepartment(department);

        Site site = new Site();
        site.setIdSite(2L);
        savedEmployee.setSite(site);

        // On spécifie le comportement du service mocké
        when(employeeService.saveEmployee(
                anyString(),  // firstname
                anyString(),  // mail
                anyString(),  // name
                anyString(),  // phone
                anyLong(),    // departmentId
                anyLong()     // siteId
        )).thenReturn(savedEmployee);

        // WHEN
        // On exécute la requête POST
        mockMvc.perform(post("/api/employees") // <-- Adaptez l'URL à votre route réelle
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoAsJson))

                // THEN
                // On vérifie que la réponse contient l'employé sauvegardé et un code 201 CREATED
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.mail").value("john.doe@example.com"))
                .andExpect(jsonPath("$.name").value("Doe"))
                .andExpect(jsonPath("$.phone").value("123456789"))
                .andExpect(jsonPath("$.department.idDepartment").value(1))
                .andExpect(jsonPath("$.site.idSite").value(2));
    }
}
