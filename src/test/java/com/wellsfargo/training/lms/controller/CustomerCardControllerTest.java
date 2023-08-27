package com.wellsfargo.training.lms.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
import com.wellsfargo.training.lms.model.CustomerCard;
import com.wellsfargo.training.lms.service.CustomerCardService;

@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class CustomerCardControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerCardService service;

    @InjectMocks
    private CustomerCardController controller;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testSaveCustomerCard() throws Exception {
        CustomerCard customerCard = new CustomerCard();
        customerCard.setId(1L);

        when(service.saveItem(any(CustomerCard.class))).thenReturn(customerCard);

        String json = new ObjectMapper().writeValueAsString(customerCard);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer_card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();

        verify(service, times(1)).saveItem(any(CustomerCard.class));
    }

    @Test
    public void testGetAllCustomerCards() throws Exception {
        List<CustomerCard> customerCards = new ArrayList<>();
        customerCards.add(new CustomerCard());
        customerCards.add(new CustomerCard());

        when(service.listAll()).thenReturn(customerCards);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/customer_card")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);

        verify(service, times(1)).listAll();
    }

    // Add more test cases for other controller methods
}
