package com.basic.transaction.services.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionWeeklyReportDto {

	private Long userId;
	private double sumAmounts;
	private long numberTransactions;
	private int weekNumber;
}
