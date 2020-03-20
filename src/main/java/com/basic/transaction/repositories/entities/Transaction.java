package com.basic.transaction.repositories.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	private String id;
	private Double amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	private String description;
	private Long userId;
	
	@PrePersist
	public void onCreate() {
		created = new Date();
	}
}
