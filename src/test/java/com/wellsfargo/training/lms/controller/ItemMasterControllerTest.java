package com.wellsfargo.training.lms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    ItemMaster itemMaster;

    @MockBean
    private ItemMasterService itemMasterService;

    @MockBean
    private CustomerCardService customerCardService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private LoanCardMasterService loanCardMasterService;

    @MockBean
    private EmployeeIssueService employeeIssueService;

    @Autowired
    private ItemMasterController controller;

    @BeforeEach
	void setUp() throws Exception {
		itemMaster= new ItemMaster();
	}

	@AfterEach
	void tearDown() throws Exception {
		itemMaster = null;
	}

    @Test
    public void testSaveItemMaster() throws Exception {
//    	RequestData requestData = new RequestData();
//        ItemMaster itemMaster = new ItemMaster();
//        itemMaster.setIssueStatus(true);
//        itemMaster.setItemCategory("Furniture");
//        itemMaster.setItemDescription("Chair");
//        itemMaster.setItemId("item123");
//        itemMaster.setItemMake("Wood");
//        itemMaster.setItemValuation(100);
//        
//        
//        
//        Customer customer = new Customer();
//        LoanCardMaster loanCardMaster = new LoanCardMaster();
//
//        requestData.setEmpId("emp123");
//        requestData.setLoanId("loan123");
//        requestData.setItemMaster(itemMaster);
//
//        when(customerService.getByEmpId("emp123")).thenReturn(Optional.of(customer));
//        when(loanCardMasterService.getByLoanId("loan123")).thenReturn(loanCardMaster);
//        when(customerCardService.saveItem(any(CustomerCard.class))).thenReturn(new CustomerCard());
//        when(itemMasterService.saveItem(itemMaster)).thenReturn(itemMaster);
//        when(employeeIssueService.saveIssue(any(EmployeeIssue.class))).thenReturn(new EmployeeIssue());
//
//        String json = new ObjectMapper().writeValueAsString(requestData);
//
//        mockMvc.perform(post("/api/item_master")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk());
//
//        verify(customerService, times(1)).getByEmpId("emp123");
//        verify(loanCardMasterService, times(1)).getByLoanId("loan123");
//        verify(customerCardService, times(1)).saveItem(any(CustomerCard.class));
//        verify(itemMasterService, times(1)).saveItem(itemMaster);
//        verify(employeeIssueService, times(1)).saveIssue(any(EmployeeIssue.class));
            RequestData requestData = new RequestData();
            requestData.setEmpId("123");
            requestData.setLoanId("456");

            Customer customer = new Customer();
            customer.setEmpId("123");

            LoanCardMaster loanCard = new LoanCardMaster();
            loanCard.setLoanId("456");

            ItemMaster itemMaster = new ItemMaster();
            requestData.setItemMaster(itemMaster);

            when(customerService.getByEmpId(requestData.getEmpId())).thenReturn(java.util.Optional.of(customer));
            when(loanCardMasterService.getByLoanId(requestData.getLoanId())).thenReturn(loanCard);
            when(itemMasterService.saveItem(itemMaster)).thenReturn(itemMaster);

            ItemMaster savedItemMaster = controller.saveItemMaster(requestData);

            assertEquals(itemMaster, savedItemMaster);
        }
    

    @Test
    public void testGetAllItemMasters() {
        List<ItemMaster> expectedList = new ArrayList<>();
        when(itemMasterService.listAll()).thenReturn(expectedList);

        List<ItemMaster> itemList = controller.getAllItemMasters();

        assertEquals(expectedList, itemList);
    }

    @Test
    public void testGetItemMasterById() throws ResourceNotFoundException {
        Long itemId = 1L;
        ItemMaster expectedItem = new ItemMaster();
        when(itemMasterService.getSingleItem(itemId)).thenReturn(java.util.Optional.of(expectedItem));

        ResponseEntity<ItemMaster> responseEntity = controller.getItemMasterById(itemId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedItem, responseEntity.getBody());
    }

    @Test
    void testUpdateItemMaster() throws ResourceNotFoundException {
        Long itemId = 1L;
        ItemMaster existingItem = new ItemMaster();
        existingItem.setItemId("ABC");
        existingItem.setItemDescription("Old Description");

        ItemMaster updatedItem = new ItemMaster();
        updatedItem.setItemId("ABC");
        updatedItem.setItemDescription("New Description");

        when(itemMasterService.getSingleItem(itemId)).thenReturn(java.util.Optional.of(existingItem));
        when(itemMasterService.saveItem(existingItem)).thenReturn(updatedItem);

        ResponseEntity<ItemMaster> responseEntity = controller.updateItemMaster(itemId, updatedItem);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedItem, responseEntity.getBody());
    }

    @Test
    void testDeleteItemMaster() throws ResourceNotFoundException {
        Long itemId = 1L;
        when(itemMasterService.getSingleItem(itemId)).thenReturn(java.util.Optional.of(new ItemMaster()));

        Map<String, Boolean> response = controller.deleteItemMaster(itemId);

        assertEquals(Boolean.TRUE, response.get("Deleted"));
    }

    @Test
    void testGetObjectById() throws ResourceNotFoundException {
        String empId = "123";
        List<Object[]> expectedList = new ArrayList<>();
        when(itemMasterService.getObjectByEmpId(empId)).thenReturn(java.util.Optional.of(expectedList));

        ResponseEntity<List<Object[]>> responseEntity = controller.getObjectById(empId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedList, responseEntity.getBody());
    }

}


