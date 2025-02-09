package com.example.contactapp.demo.tests.testsController;

import com.example.contactapp.demo.ServiceApi.SiteService;
import com.example.contactapp.demo.jpa.Site;
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
class GetAllSiteTest {

    @Autowired
    private MockMvc mockMvc;


    @Mock
    private SiteService siteService;

    @Test
    void testGetAllSite() throws Exception {
        // Préparation des données factices
        Site site1 = new Site();
        Mockito.when(siteService.getAllSites()).thenReturn(asList(site1));

        // Appel de l'endpoint /departments
        mockMvc.perform(get("/api/site")
                        .contentType(APPLICATION_JSON))
                // Vérifie que la requête renvoie un statut 200 OK
                .andExpect(status().isOk())
                // Vérifie que la taille du tableau JSON renvoyé est de 3
                .andExpect(jsonPath("$.size()").value(9));

    }
}
