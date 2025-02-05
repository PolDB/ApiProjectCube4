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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SiteController.class)
class UpdateSiteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteService service;

    @MockBean // <-- Mock du service EmployeeService
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testUpdateSite() throws Exception {
        // GIVEN
        Long siteId = 4L;

        // Corps de la requête PUT (ce que tu envoies pour la mise à jour)
        Site requestBody = new Site();
        requestBody.setCity("New name");

        // Ce que renverra ton service updateDepartment
        Site updateSite = new Site();
        updateSite.setIdSite(siteId);
        updateSite.setCity("New name");

        // On simule le comportement du service
        Mockito.when(service.updateSite(any(Site.class)))
                .thenReturn(updateSite);

        // WHEN - on appelle PUT /departments/42 (ou /api/department/42 selon ton vrai mapping)
        mockMvc.perform(put("/api/site/{id}", siteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))

                // THEN - vérifications sur la réponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSite").value(siteId))
                .andExpect(jsonPath("$.city").value("New name"));

        // On peut également vérifier que le service a bien été appelé une seule fois
        verify(service, times(1)).updateSite(any(Site.class));
    }
}
