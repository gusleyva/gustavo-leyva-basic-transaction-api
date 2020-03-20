package com.basic.transaction.services.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionReportDto {

	private Long userId;
	private Date weekStart;
	private Date weekFinish;
	private double quantity;
	private double amount;
	private double totalAmount;
}
