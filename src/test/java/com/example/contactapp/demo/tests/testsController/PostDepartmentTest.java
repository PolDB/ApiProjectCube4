package com.example.contactapp.demo.tests.testsController;

import com.example.contactapp.demo.ControlerApi.DepartmentController;
import com.example.contactapp.demo.ServiceApi.EmployeeService;
import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.ServiceApi.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;  // <-- Import de MockBean
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class PostDepartmentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  // <-- On déclare un mock de DepartmentService pour qu'il soit dans le contexte Spring
    private DepartmentService departmentService;

    @MockBean  // <-- Idem, on déclare un mock de EmployeeService
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddDepartment() throws Exception {
        // GIVEN
        Department departmentToSave = new Department();
        departmentToSave.setDepartment_name("Informatique");

        // On configure le mock : quand on appelle saveDepartment sur departmentService,
        // on renvoie l'objet departmentToSave
        Mockito.when(departmentService.saveDepartment(any(Department.class)))
                .thenReturn(departmentToSave);

        // WHEN - on exécute la requête POST /api/department
        mockMvc.perform(post("/api/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentToSave)))
                // THEN - on vérifie le code HTTP
                .andExpect(status().isOk());
    }
}
