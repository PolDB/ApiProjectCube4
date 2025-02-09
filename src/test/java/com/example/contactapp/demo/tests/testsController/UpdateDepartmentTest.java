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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class UpdateDepartmentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService service;

    @MockBean // <-- Mock du service EmployeeService
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testUpdateDepartment() throws Exception {
        // GIVEN
        Long departmentId = 4L;

        // Corps de la requête PUT (ce que tu envoies pour la mise à jour)
        Department requestBody = new Department();
        requestBody.setDepartment_name("New name");

        // Ce que renverra ton service updateDepartment
        Department updatedDepartment = new Department();
        updatedDepartment.setIdDepartment(departmentId);
        updatedDepartment.setDepartment_name("New name");

        // On simule le comportement du service
        Mockito.when(service.updateDepartment(any(Department.class)))
                .thenReturn(updatedDepartment);

        // WHEN - on appelle PUT /departments/42 (ou /api/department/42 selon ton vrai mapping)
        mockMvc.perform(put("/api/department/{id}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))

                // THEN - vérifications sur la réponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDepartment").value(departmentId))
                .andExpect(jsonPath("$.department_name").value("New name"));

        // On peut également vérifier que le service a bien été appelé une seule fois
        verify(service, times(1)).updateDepartment(any(Department.class));
    }
}
