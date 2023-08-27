package com.wellsfargo.training.lms.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.wellsfargo.training.lms.model.Customer;
import com.wellsfargo.training.lms.model.CustomerCard;
import com.wellsfargo.training.lms.model.EmployeeIssue;
import com.wellsfargo.training.lms.model.ItemMaster;
import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.model.RequestData;
import com.wellsfargo.training.lms.service.CustomerCardService;
import com.wellsfargo.training.lms.service.CustomerService;
import com.wellsfargo.training.lms.service.EmployeeIssueService;
import com.wellsfargo.training.lms.service.ItemMasterService;
import com.wellsfargo.training.lms.service.LoanCardMasterService;

@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class ItemMasterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemMasterService itemMasterService;

    @Mock
    private CustomerCardService customerCardService;

    @Mock
    private CustomerService customerService;

    @Mock
    private LoanCardMasterService loanCardMasterService;

    @Mock
    private EmployeeIssueService employeeIssueService;

    @InjectMocks
    private ItemMasterController controller;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testSaveItemMaster() throws Exception {
    	RequestData requestData = new RequestData();
        ItemMaster itemMaster = new ItemMaster();
        itemMaster.setIssueStatus(true);
        itemMaster.setItemCategory("Furniture");
        itemMaster.setItemDescription("Chair");
        itemMaster.setItemId("item123");
        itemMaster.setItemMake("Wood");
        itemMaster.setItemValuation(100);
        
        
        
        Customer customer = new Customer();
        LoanCardMaster loanCardMaster = new LoanCardMaster();

        requestData.setEmpId("emp123");
        requestData.setLoanId("loan123");
        requestData.setItemMaster(itemMaster);

        when(customerService.getByEmpId("emp123")).thenReturn(Optional.of(customer));
        when(loanCardMasterService.getByLoanId("loan123")).thenReturn(loanCardMaster);
        when(customerCardService.saveItem(any(CustomerCard.class))).thenReturn(new CustomerCard());
        when(itemMasterService.saveItem(itemMaster)).thenReturn(itemMaster);
        when(employeeIssueService.saveIssue(any(EmployeeIssue.class))).thenReturn(new EmployeeIssue());

        String json = new ObjectMapper().writeValueAsString(requestData);

        mockMvc.perform(post("/api/item_master")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(customerService, times(1)).getByEmpId("emp123");
        verify(loanCardMasterService, times(1)).getByLoanId("loan123");
        verify(customerCardService, times(1)).saveItem(any(CustomerCard.class));
        verify(itemMasterService, times(1)).saveItem(itemMaster);
        verify(employeeIssueService, times(1)).saveIssue(any(EmployeeIssue.class));
    }


    @Test
    public void testGetAllItemMasters() throws Exception {
        List<ItemMaster> itemMasters = new ArrayList<>();
        itemMasters.add(new ItemMaster());
        itemMasters.add(new ItemMaster());

        when(itemMasterService.listAll()).thenReturn(itemMasters);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/item_master")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);

        verify(itemMasterService, times(1)).listAll();
    }

    @Test
    public void testGetItemMasterById() throws Exception {
        ItemMaster itemMaster = new ItemMaster();
        itemMaster.setId(1L);

        when(itemMasterService.getSingleItem(1L)).thenReturn(Optional.of(itemMaster));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/item_master/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);

        verify(itemMasterService, times(1)).getSingleItem(1L);
    }

    @Test
    public void testUpdateItemMaster() throws Exception {
        ItemMaster itemMaster = new ItemMaster();
        itemMaster.setId(1L);

        when(itemMasterService.getSingleItem(1L)).thenReturn(Optional.of(itemMaster));
        when(itemMasterService.saveItem(any(ItemMaster.class))).thenReturn(itemMaster);

        String json = new ObjectMapper().writeValueAsString(itemMaster);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/item_master/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();

        verify(itemMasterService, times(1)).saveItem(any(ItemMaster.class));
    }

    @Test
    public void testDeleteItemMaster() throws Exception {
        when(itemMasterService.getSingleItem(1L)).thenReturn(Optional.of(new ItemMaster()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/item_master/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        verify(itemMasterService, times(1)).deleteItem(1L);
    }

    // Add more test cases for other controller methods
}
