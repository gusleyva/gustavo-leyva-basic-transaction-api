package com.basic.transaction.repositories.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionAccumulated {

	private Long userId;
    private double amount;
 
    public TransactionAccumulated(Long userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
}
