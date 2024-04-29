package com.qsp.udharchand_app.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	@Column(unique = true)
	private long userPhoneNo;
	@Column(unique = true)
	private String userEmail;
	private String address;
	private String userType;
	private double totalAmt;
	@OneToMany(cascade = CascadeType.ALL)
	private List<UserTransaction> userTransactions;
}
