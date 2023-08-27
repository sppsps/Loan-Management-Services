package com.wellsfargo.training.lms.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.training.lms.exceptions.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.EmployeeIssue;
import com.wellsfargo.training.lms.service.EmployeeIssueService;

@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class EmployeeIssueControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeIssueService service;

    @InjectMocks
    private EmployeeIssueController controller;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testSaveIssue() throws Exception {
        EmployeeIssue employeeIssue = new EmployeeIssue();
        employeeIssue.setId(1L);

        when(service.saveIssue(any(EmployeeIssue.class))).thenReturn(employeeIssue);

        String json = new ObjectMapper().writeValueAsString(employeeIssue);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/emp_issue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();

        verify(service, times(1)).saveIssue(any(EmployeeIssue.class));
    }

    @Test
    public void testGetEmployeeIssueById() throws Exception {
        EmployeeIssue employeeIssue = new EmployeeIssue();
        employeeIssue.setId(1L);

        when(service.getSingleItem(1L)).thenReturn(Optional.of(employeeIssue));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/emp_issues/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);

        verify(service, times(1)).getSingleItem(1L);
    }

    @Test
    public void testGetEmployeeIssueByEmp() throws Exception {
        List<EmployeeIssue> employeeIssues = new ArrayList<>();
        employeeIssues.add(new EmployeeIssue());
        employeeIssues.add(new EmployeeIssue());

        when(service.getById("123")).thenReturn(employeeIssues);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/emp_issues/emp/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);

        verify(service, times(1)).getById("123");
    }

    @Test
    public void testDeleteEmployeeIssue() throws Exception {
        when(service.getSingleItem(1L)).thenReturn(Optional.of(new EmployeeIssue()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/emp_issue/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        verify(service, times(1)).deleteItem(1L);
    }

    @Test
    public void testUpdateEmployeeIssue() throws Exception {
        EmployeeIssue employeeIssue = new EmployeeIssue();
        employeeIssue.setId(1L);

        when(service.getSingleItem(1L)).thenReturn(Optional.of(employeeIssue));
        when(service.saveIssue(any(EmployeeIssue.class))).thenReturn(employeeIssue);

        String json = new ObjectMapper().writeValueAsString(employeeIssue);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/emp_issue/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();

        verify(service, times(1)).saveIssue(any(EmployeeIssue.class));
    }

}
