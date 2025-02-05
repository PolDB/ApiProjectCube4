package com.example.contactapp.demo.testsController;

import com.example.contactapp.demo.ControlerApi.DepartmentController;
import com.example.contactapp.demo.ControlerApi.SiteController;
import com.example.contactapp.demo.ServiceApi.EmployeeService;
import com.example.contactapp.demo.ServiceApi.SiteService;
import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.ServiceApi.DepartmentService;
import com.example.contactapp.demo.jpa.Site;
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

@WebMvcTest(SiteController.class)
class PostSiteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  // <-- On déclare un mock de DepartmentService pour qu'il soit dans le contexte Spring
    private SiteService siteService;

    @MockBean  // <-- Idem, on déclare un mock de EmployeeService
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddSite() throws Exception {
        // GIVEN
        Site siteToSave = new Site();
        siteToSave.setCity("Informatique");

        // On configure le mock : quand on appelle saveDepartment sur departmentService,
        // on renvoie l'objet departmentToSave
        Mockito.when(siteService.saveSite(any(Site.class)))
                .thenReturn(siteToSave);

        // WHEN - on exécute la requête POST /api/department
        mockMvc.perform(post("/api/site")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(siteToSave)))
                // THEN - on vérifie le code HTTP
                .andExpect(status().isOk());
    }
}
