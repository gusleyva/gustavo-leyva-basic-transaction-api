package com.basic.transaction.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.basic.transaction.repositories.TransactionRepository;
import com.basic.transaction.repositories.entities.Transaction;
import com.basic.transaction.services.dto.TransactionAccumulatedDto;
import com.basic.transaction.services.dto.TransactionDto;

import org.springframework.test.context.ContextConfiguration;

public class TransactionServiceTest {
	
	TransactionService transactionServiceSpy;
	
	@MockBean
	TransactionRepository mockRepository;
	
	@Before
	public void setUp() {
		transactionServiceSpy = spy(new TransactionService());
	}
	
	@Ignore
	public void getByIdTest() {
		String id = "dummyId";
		Transaction transaction = getTransaction(id, 10000);
		Optional<Transaction> optTransaction = Optional.of(transaction);
		when(mockRepository.findById(id)).thenReturn(optTransaction);
		//doReturn(optTransaction).when(mockRepository).findById(Mockito.anyString());
		when(mockRepository.findById(id)).thenReturn(optTransaction);
		TransactionDto transactionDto = transactionServiceSpy.getById(id);
		assertTrue(transactionDto != null);
		assertEquals(id, transactionDto.getId());
	}
	
	@Test
	public void getAccumulatedAmountTest() {
		Long userId = 0L;
		Double expected = 0.0;
		TransactionAccumulatedDto transactionAccumulatedDto = 
				transactionServiceSpy.getAccumulatedAmount(userId, Collections.emptyList());
		assertNotNull(transactionAccumulatedDto);
		assertTrue(expected.compareTo(transactionAccumulatedDto.getAmount()) == 0);
		
		transactionAccumulatedDto = 
				transactionServiceSpy.getAccumulatedAmount(userId, null);
		assertNotNull(transactionAccumulatedDto);
		assertTrue(expected.compareTo(transactionAccumulatedDto.getAmount()) == 0);
		
		expected = 5010.0;
		List<TransactionDto> listTransactionDto = getListTransactionDto(5);
		transactionAccumulatedDto = 
				transactionServiceSpy.getAccumulatedAmount(userId, listTransactionDto);
		assertNotNull(transactionAccumulatedDto);
		assertTrue(expected.compareTo(transactionAccumulatedDto.getAmount()) == 0);
		
	}
	
	@Test
	public void getRandomTransactionTest() {
		List<TransactionDto> listTransactionDto =  getListTransactionDto(5);
		doReturn(listTransactionDto).when(transactionServiceSpy).getAll();
		TransactionDto randomTransactionDto = transactionServiceSpy.getRandomTransaction();
		assertTrue(randomTransactionDto != null);
		assertTrue(randomTransactionDto.getAmount() > 10);
		
	}
	
	@Test
	public void getTransactionIdTest() {
		String transactionId = transactionServiceSpy.getTransactionId();
		assertTrue(transactionId.length() > 0);
		String[] arrayPartsTransactionId = transactionId.split("-");
		assertEquals(6, arrayPartsTransactionId.length);
	}
	
	private List<TransactionDto> getListTransactionDto(int numberRecords){
		List<TransactionDto> listTransactionDto = new ArrayList<>();
		for(int i = 0; i < numberRecords; i++) {
			listTransactionDto.add(getTransactionDto("100"+i, 1000+i));
		}
		return listTransactionDto;
	}
	
	private TransactionDto getTransactionDto(String id, double amount){
		TransactionDto mockTransactionDto = new TransactionDto();
		mockTransactionDto.setId(id);
		mockTransactionDto.setAmount(amount);
		mockTransactionDto.setCreated(new Date());
		return mockTransactionDto;
	}
	
	private Transaction getTransaction(String id, double amount){
		Transaction mockTransaction = new Transaction();
		mockTransaction.setId(id);
		mockTransaction.setAmount(amount);
		mockTransaction.setCreated(new Date());
		return mockTransaction;
	}
}
