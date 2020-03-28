package com.basic.transaction.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import com.basic.transaction.services.dto.TransactionReportDto;

import org.springframework.test.context.ContextConfiguration;

public class TransactionServiceTest {
	
	TransactionService transactionServiceSpy;
	
	@MockBean
	TransactionRepository mockRepository;
	
	@Before
	public void setUp() {
		transactionServiceSpy = spy(new TransactionService());
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
	public void getTransactionWeeklyReportTest() {
		List<TransactionDto> listTransactionDto  = getTransactionDtoSpecificDate();
		assertEquals(19, listTransactionDto.size());
		
		List<TransactionReportDto> transactionReportDtoList = transactionServiceSpy.getTransactionWeeklyReport(0L, listTransactionDto);
		assertTrue(!transactionReportDtoList.isEmpty());
		
		assertEquals(6, transactionReportDtoList.size());
		
		TransactionReportDto tmpTransactionReportDto = transactionReportDtoList.get(0);
		assertTrue(tmpTransactionReportDto.getQuantity() == 2);
		assertTrue(tmpTransactionReportDto.getAmount() == 10);
		assertTrue(tmpTransactionReportDto.getTotalAmount() == 0);
		
		tmpTransactionReportDto = transactionReportDtoList.get(5);
		assertTrue(tmpTransactionReportDto.getQuantity() == 3);
		assertTrue(tmpTransactionReportDto.getAmount() == 100);
		assertTrue(tmpTransactionReportDto.getTotalAmount() == 300);
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
	
	private TransactionDto getTransactionDto(String id, double amount, String specificDate, Long userId){
		TransactionDto mockTransactionDto = new TransactionDto();
		mockTransactionDto.setId(id);
		mockTransactionDto.setAmount(amount);
		mockTransactionDto.setCreated(getSpecificDate(specificDate));
		mockTransactionDto.setUserId(userId);
		return mockTransactionDto;
	}
	
	private Date getSpecificDate(String specificDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
		LocalDateTime dateSpecific = LocalDateTime.parse(specificDate, formatter);
		Date date = Date.from(dateSpecific.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}
	
	private Transaction getTransaction(String id, double amount){
		Transaction mockTransaction = new Transaction();
		mockTransaction.setId(id);
		mockTransaction.setAmount(amount);
		mockTransaction.setCreated(new Date());
		return mockTransaction;
	}
	
	private List<TransactionDto> getTransactionDtoSpecificDate(){
		List<TransactionDto> listTransactionDto = new ArrayList<>();
		Long userId = 0L;
		TransactionDto transactionDto1 = getTransactionDto("transaction-1", 5.00, "2019-11-09 10:01:35.59", userId);
		TransactionDto transactionDto2 = getTransactionDto("transaction-2", 5.00, "2019-11-08 10:01:35.59", userId);
		
		TransactionDto transactionDto3 = getTransactionDto("transaction-3", 20.00, "2019-11-16 10:01:35.59", userId);
		
		TransactionDto transactionDto4 = getTransactionDto("transaction-4", 25.00, "2019-11-23 10:01:35.59", userId);
		TransactionDto transactionDto5 = getTransactionDto("transaction-5", 25.00, "2019-11-24 10:01:35.59", userId);
		TransactionDto transactionDto6 = getTransactionDto("transaction-6", 25.00, "2019-11-25 10:01:35.59", userId);
		TransactionDto transactionDto7 = getTransactionDto("transaction-7", 25.00, "2019-11-26 10:01:35.59", userId);
		
		TransactionDto transactionDto8 = getTransactionDto("transaction-8", 20.00, "2019-11-29 10:01:35.59", userId);
		TransactionDto transactionDto9 = getTransactionDto("transaction-9", 20.00, "2019-11-30 10:01:35.59", userId);
		TransactionDto transactionDto10 = getTransactionDto("transaction-10", 10.00, "2019-11-30 10:01:35.59", userId);
		
		TransactionDto transactionDto11 = getTransactionDto("transaction-11", 20.00, "2019-12-01 10:01:35.59", userId);
		TransactionDto transactionDto12 = getTransactionDto("transaction-12", 20.00, "2019-12-02 10:01:35.59", userId);
		TransactionDto transactionDto13 = getTransactionDto("transaction-13", 20.00, "2019-12-03 10:01:35.59", userId);
		TransactionDto transactionDto14 = getTransactionDto("transaction-14", 20.00, "2019-12-03 10:01:35.59", userId);
		TransactionDto transactionDto15 = getTransactionDto("transaction-15", 20.00, "2019-12-04 10:01:35.59", userId);
		TransactionDto transactionDto16 = getTransactionDto("transaction-16", 20.00, "2019-12-05 10:01:35.59", userId);
		
		TransactionDto transactionDto17 = getTransactionDto("transaction-17", 45.00, "2019-12-06 10:01:35.59", userId);
		TransactionDto transactionDto18 = getTransactionDto("transaction-19", 45.00, "2019-12-08 10:01:35.59", userId);
		TransactionDto transactionDto19 = getTransactionDto("transaction-19", 10.00, "2019-12-11 10:01:35.59", userId);
		
		listTransactionDto.add(transactionDto1);
		listTransactionDto.add(transactionDto2);
		listTransactionDto.add(transactionDto3);
		listTransactionDto.add(transactionDto4);
		listTransactionDto.add(transactionDto5);
		listTransactionDto.add(transactionDto6);
		listTransactionDto.add(transactionDto7);
		listTransactionDto.add(transactionDto8);
		listTransactionDto.add(transactionDto9);
		listTransactionDto.add(transactionDto10);
		listTransactionDto.add(transactionDto11);
		listTransactionDto.add(transactionDto12);
		listTransactionDto.add(transactionDto13);
		listTransactionDto.add(transactionDto14);
		listTransactionDto.add(transactionDto15);
		listTransactionDto.add(transactionDto16);
		listTransactionDto.add(transactionDto17);
		listTransactionDto.add(transactionDto18);
		listTransactionDto.add(transactionDto19);
		
		return listTransactionDto;
	}
}
