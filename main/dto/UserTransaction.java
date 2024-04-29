package com.qsp.udharchand_app.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userTransactionId;
	private String description;
	private String payMode;
	private boolean transactionType;
	private String date;
	private double transactionAmount;
}
