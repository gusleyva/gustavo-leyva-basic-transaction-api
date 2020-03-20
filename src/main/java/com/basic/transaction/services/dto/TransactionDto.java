package com.basic.transaction.services.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionDto {
	
	private String id;
	private Double amount;
	private Date created;
	private String description;
	private Long userId;
}
