package com.example.contactapp.demo.testsController;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.contactapp.demo.ControlerApi.EmployeeController;
import com.example.contactapp.demo.jpa.Employee;
import com.example.contactapp.demo.jpa.EmployeeDTO;
import com.example.contactapp.demo.ServiceApi.EmployeeService;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class GetAllEmployeeTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    @Test
    void testGetAllEmployees() {
        // GIVEN
        List<Employee> mockEmployees = Arrays.asList(
                new Employee()
        );

        // On prépare le mock du service
        when(service.getAllEmployees()).thenReturn(mockEmployees);

        // WHEN
        List<EmployeeDTO> employeeDTOs = controller.getAllEmployees();

        // THEN
        // On vérifie qu'on récupère bien 2 éléments
        assertThat(employeeDTOs).hasSize(1);


    }
}
