package com.qsp.udharchand_app.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int walletId;
	private double walletAmt;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Transaction> transactions;
}
