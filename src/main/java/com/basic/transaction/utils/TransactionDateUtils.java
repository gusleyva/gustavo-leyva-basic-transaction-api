package com.basic.transaction.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.basic.transaction.services.dto.TransactionDto;

public class TransactionDateUtils {

	//Getting the default zone id
	private static ZoneId defaultZoneId = ZoneId.systemDefault();
	//private static ZoneId usCentral = ZoneId.of("America/Chicago");
	
	public static Date getMinorDate(List<TransactionDto> listTransactionDto) {
		return listTransactionDto != null && !listTransactionDto.isEmpty() ? listTransactionDto.get(0).getCreated() : null;
	}
	
	public static LocalDateTime getLocalDateFromDate(Date currentDate) {
		return currentDate.toInstant().atZone(defaultZoneId).toLocalDateTime();
	}
	
	public static Date getDateFromLocalDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(defaultZoneId).toInstant());
	}
	
	public static Date getNextThursday(Date currentDate) {
		LocalDateTime localDate = getLocalDateFromDate(currentDate);
		return getDateFromLocalDate(localDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)));
	}
	
	public static Date getPreviousFriday(Date currentDate) {
		LocalDateTime localDate = getLocalDateFromDate(currentDate);
		return getDateFromLocalDate(localDate.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)));
	}
	
	public static Date getDatePlusNumberDays(Date currentDate, long days) {
		LocalDateTime localDate = getLocalDateFromDate(currentDate);
		return getDateFromLocalDate(localDate.plusDays(days));
	}
	
	
	
	public static Date getWeekFinishDate(Date weekStartDate) {
		Date weekFinishDate = getNextThursday(weekStartDate);
		//Validate if is next month
		Date firstDayNextMonth = getFirstDayOfNextMonth(weekStartDate);
		
		return weekFinishDate.compareTo(firstDayNextMonth) >= 0 ? getLastDateOfMonth(weekStartDate) : weekFinishDate;
	}
	
	/**
	 * Get date to first day of next month
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getFirstDayOfNextMonth(Date currentDate) {
		LocalDateTime localDate = getLocalDateFromDate(currentDate);
		return getDateFromLocalDate(localDate.with(TemporalAdjusters.firstDayOfNextMonth()));
	}
	
	public static Date getLastDateOfMonth(Date currentDate) {
		LocalDateTime localDate = getLocalDateFromDate(currentDate);
		return getDateFromLocalDate(localDate.with(TemporalAdjusters.lastDayOfMonth()));
	}
}
