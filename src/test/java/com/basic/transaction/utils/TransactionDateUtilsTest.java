package com.basic.transaction.utils;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TransactionDateUtilsTest {
	
	private Date testingDate;
	@Before
	public void setUp() {
		testingDate = getSpecificDate("2020-03-23 10:01:35.59");
	}
	
	@Test
	public void getWeekStartDateTest() {
		Date expectedDate = getSpecificDate("2020-03-20 10:01:35.59");
		Date weekStartDate = TransactionDateUtils.getPreviousFriday(testingDate);
		assertEquals(expectedDate, weekStartDate);
		
		testingDate = getSpecificDate("2020-03-24 10:01:35.59");
		weekStartDate = TransactionDateUtils.getPreviousFriday(testingDate);
		assertEquals(expectedDate, weekStartDate);
		
		testingDate = getSpecificDate("2020-03-26 10:01:35.59");
		weekStartDate = TransactionDateUtils.getPreviousFriday(testingDate);
		assertEquals(expectedDate, weekStartDate);
		
		testingDate = getSpecificDate("2020-03-27 10:01:35.59");
		weekStartDate = TransactionDateUtils.getPreviousFriday(testingDate);
		assertEquals(expectedDate, weekStartDate);
		
		expectedDate = getSpecificDate("2020-03-27 10:01:35.59");
		testingDate = getSpecificDate("2020-04-03 10:01:35.59");
		weekStartDate = TransactionDateUtils.getPreviousFriday(testingDate);
		assertEquals(expectedDate, weekStartDate);
	}
	
	@Test
	public void getWeekDateTest() {
		Date expectedDate = getSpecificDate("2020-03-24 10:01:35.59");
		Date weekStartDate = TransactionDateUtils.getDatePlusNumberDays(testingDate, 1);
		assertEquals(expectedDate, weekStartDate);
		

		expectedDate = getSpecificDate("2020-04-01 10:01:35.59");
		testingDate = getSpecificDate("2020-03-31 10:01:35.59");
		weekStartDate = TransactionDateUtils.getDatePlusNumberDays(testingDate, 1);
		assertEquals(expectedDate, weekStartDate);
	}
	
	@Test
	public void getFirstDayOfNextMonthTest() {
		Date expectedDate = getSpecificDate("2020-04-01 10:01:35.59");
		Date firstDateNextMonth = TransactionDateUtils.getFirstDayOfNextMonth(testingDate);
		assertEquals(expectedDate, firstDateNextMonth);
	}
	
	@Test
	public void getLastDateOfMonthTest() {
		Date expectedDate = getSpecificDate("2020-03-31 10:01:35.59");
		Date lastDateOfMonth = TransactionDateUtils.getLastDateOfMonth(testingDate);
		assertEquals(expectedDate, lastDateOfMonth);
	}
	
	@Test
	public void getWeekFinishDateTest() {
		Date expectedDate = getSpecificDate("2020-03-26 10:01:35.59");
		testingDate = getSpecificDate("2020-03-20 10:01:35.59");
		Date weekFinishDate = TransactionDateUtils.getWeekFinishDate(testingDate);
		assertEquals(expectedDate, weekFinishDate);
		
		expectedDate = getSpecificDate("2020-03-31 10:01:35.59");
		testingDate = getSpecificDate("2020-03-26 10:01:35.59");
		weekFinishDate = TransactionDateUtils.getWeekFinishDate(testingDate);
		assertEquals(expectedDate, weekFinishDate);
		
		expectedDate = getSpecificDate("2020-04-02 10:01:35.59");
		testingDate = getSpecificDate("2020-04-01 10:01:35.59");
		weekFinishDate = TransactionDateUtils.getWeekFinishDate(testingDate);
		assertEquals(expectedDate, weekFinishDate);
	}
	
	@Test
	public void getNextThursdayTest() {
		Date expectedDate = getSpecificDate("2020-03-26 10:01:35.59");
		testingDate = getSpecificDate("2020-03-20 10:01:35.59");
		Date weekFinishDate = TransactionDateUtils.getNextThursday(testingDate);
		assertEquals(expectedDate, weekFinishDate);
		
		expectedDate = getSpecificDate("2020-04-02 10:01:35.59");
		testingDate = getSpecificDate("2020-04-01 10:01:35.59");
		weekFinishDate = TransactionDateUtils.getNextThursday(testingDate);
		assertEquals(expectedDate, weekFinishDate);
	}
	
	private Date getSpecificDate(String specificDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
		LocalDateTime dateSpecific = LocalDateTime.parse(specificDate, formatter);
		Date date = Date.from(dateSpecific.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}
}
