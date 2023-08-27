package com.wellsfargo.training.lms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wellsfargo.training.lms.exceptions.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.service.LoanCardMasterService;

@SpringBootTest
class LoanCardMasterTest {

	LoanCardMaster lcm;
	
	@MockBean
    private LoanCardMasterService lservice;

    @Autowired
    private LoanCardMasterController loanCardMasterController;

    @BeforeEach
	void setUp() throws Exception {
		lcm = new LoanCardMaster();
	}

	@AfterEach
	void tearDown() throws Exception {
		lcm = null;
	}
    
    @Test
    void testSaveCard() {
        lcm.setLoanId("123");
        lcm.setLoanType("Home Loan");
        lcm.setDuration(10);

        when(lservice.saveLoanCard(any(LoanCardMaster.class))).thenReturn(lcm);

        LoanCardMaster savedCard = loanCardMasterController.saveCard(lcm);

        assertEquals(lcm.getLoanId(), savedCard.getLoanId());
        assertEquals(lcm.getLoanType(), savedCard.getLoanType());
        assertEquals(lcm.getDuration(), savedCard.getDuration());
    }

    @Test
    void testGetAllCards() {
        List<LoanCardMaster> cards = new ArrayList<>();
        LoanCardMaster l1 = new LoanCardMaster();
        l1.setLoanId("123");
        l1.setLoanType("Furniture");
        l1.setDuration(5);
        
        LoanCardMaster l2 = new LoanCardMaster();
        l2.setLoanId("124");
        l2.setLoanType("Wooden");
        l2.setDuration(2);
        
        cards.add(l2);
        cards.add(l1);
        

        when(lservice.listAll()).thenReturn(cards);

        List<LoanCardMaster> response = loanCardMasterController.getAllCards();

        assertEquals(cards.size(), response.size());
        assertEquals(cards, response);
    }

    @Test
    void testDeleteLoanCard() throws ResourceNotFoundException {
        Long cardIdToDelete = 1L;

        LoanCardMaster l1 = new LoanCardMaster();
        l1.setLoanId("123");
        l1.setLoanType("Furniture");
        l1.setDuration(5);

        when(lservice.getSingleItem(cardIdToDelete)).thenReturn(Optional.of(l1));

        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("Deleted", true);

        Map<String, Boolean> response = loanCardMasterController.deleteLoanCard(cardIdToDelete);

        assertEquals(expectedResponse, response);
        verify(lservice, times(1)).deleteItem(cardIdToDelete);
    }

    @Test
    void testUpdateLoanCard() throws ResourceNotFoundException {
        Long cardIdToUpdate = 1L;

        LoanCardMaster l1 = new LoanCardMaster();
        l1.setLoanId("123");
        l1.setLoanType("Furniture");
        l1.setDuration(5);
        
        LoanCardMaster l2 = new LoanCardMaster();
        l2.setLoanId("124");
        l2.setLoanType("Wooden");
        l2.setDuration(2);

        when(lservice.getSingleItem(cardIdToUpdate)).thenReturn(Optional.of(l1));
        when(lservice.saveLoanCard(any(LoanCardMaster.class))).thenReturn(l2);

        ResponseEntity<LoanCardMaster> response = loanCardMasterController.updateLoanCard(cardIdToUpdate, l2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(l2, response.getBody());
        verify(lservice, times(1)).saveLoanCard(l1);
    }
    
    

    // Add tests for other functions as needed

}
