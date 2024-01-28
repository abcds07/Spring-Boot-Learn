package com.abhisheksingh.SpringBootLearn.controller;

import com.abhisheksingh.SpringBootLearn.entity.Department;
import com.abhisheksingh.SpringBootLearn.error.DepartmentNotFoundException;
import com.abhisheksingh.SpringBootLearn.service.DepartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-06")
                .departmentAddress("Ahmedabad")
                .departmentId(2L)
                .build();
    }


    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-06")
                .departmentAddress("Ahmedabad")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"IT\",\n" +
                        "    \"departmentAddress\":\"Ahmedabad\",\n" +
                        "    \"departmentCode\":\"IT-06\"\n" +
                        "}")).andExpect(status().isOk());
    }

}