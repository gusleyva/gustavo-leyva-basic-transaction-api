package com.basic.transaction.repositories.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionWeeklyReport {
	
	private long userId;
	private double sumAmounts;
	private long numberTransactions;
	private int weekNumber;
	
	public TransactionWeeklyReport(long userId, double sumAmounts, long numberTransactions, int weekNumber) {
		this.userId = userId;
		this.sumAmounts = sumAmounts;
		this.numberTransactions = numberTransactions;
		this.weekNumber = weekNumber;
	}
}
