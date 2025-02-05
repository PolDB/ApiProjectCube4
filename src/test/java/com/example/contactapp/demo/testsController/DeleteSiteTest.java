package com.example.contactapp.demo.testsController;

import com.example.contactapp.demo.ControlerApi.DepartmentController;
import com.example.contactapp.demo.ControlerApi.SiteController;
import com.example.contactapp.demo.ServiceApi.EmployeeService;
import com.example.contactapp.demo.ServiceApi.DepartmentService;
import com.example.contactapp.demo.ServiceApi.SiteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SiteController.class)
class DeleteSiteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteService siteService;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testDeleteSite() throws Exception {
        // GIVEN
        Long siteId = 4L;
        String expectedMessage = "Site deleted successfully";

        // On simule la réponse de departmentService.deleteDepartment()
        Mockito.when(siteService.deleteSite(anyLong()))
                .thenReturn(expectedMessage);

        // WHEN - On envoie une requête DELETE vers /api/department/42
        mockMvc.perform(delete("/api/site/{id}", siteId))

                // THEN - On vérifie la réponse
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMessage));

        // On peut vérifier que departmentService.deleteDepartment a bien été appelé
        Mockito.verify(siteService).deleteSite(siteId);
    }
}
