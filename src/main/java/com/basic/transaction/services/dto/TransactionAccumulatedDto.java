package com.basic.transaction.services.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionAccumulatedDto {

	private Long userId;
    private double amount;
}
