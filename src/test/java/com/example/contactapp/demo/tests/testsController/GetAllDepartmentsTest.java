package com.example.contactapp.demo.tests.testsController;

import com.example.contactapp.demo.ServiceApi.DepartmentService;
import com.example.contactapp.demo.jpa.Department;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GetAllDepartmentsTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @Test
    void testGetAllDepartments() throws Exception {
        // Préparation des données factices
        Department department1 = new Department();
        Department department2 = new Department();
        Mockito.when(departmentService.getAllDepartments()).thenReturn(asList(department1, department2));

        // Appel de l'endpoint /departments
        mockMvc.perform(get("/api/department")
                        .contentType(APPLICATION_JSON))
                // Vérifie que la requête renvoie un statut 200 OK
                .andExpect(status().isOk())
                // Vérifie que la taille du tableau JSON renvoyé est de 3
                .andExpect(jsonPath("$.size()").value(3));

    }
}
