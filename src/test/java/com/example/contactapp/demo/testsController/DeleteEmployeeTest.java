package com.example.contactapp.demo.testsController;

import com.example.contactapp.demo.ControlerApi.DepartmentController;
import com.example.contactapp.demo.ControlerApi.EmployeeController;
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

@WebMvcTest(EmployeeController.class)
class DeleteEmployeeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testDeleteEmployee() throws Exception {
        // GIVEN
        Long employeeId = 4L;
        String expectedMessage = "Site deleted successfully";

        // On simule la réponse de departmentService.deleteDepartment()
        Mockito.when(employeeService.deleteEmployee(anyLong()))
                .thenReturn(expectedMessage);

        // WHEN - On envoie une requête DELETE vers /api/department/42
        mockMvc.perform(delete("/api/employees/{id}", employeeId))

                // THEN - On vérifie la réponse
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMessage));

        // On peut vérifier que departmentService.deleteDepartment a bien été appelé
        Mockito.verify(employeeService).deleteEmployee(employeeId);
    }
}
