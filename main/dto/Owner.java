package com.qsp.udharchand_app.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ownerId;
	@NotNull(message = "name cant be null")
	@NotBlank(message = "name cant be blank")
	private String ownerName;
	@Column(unique = true,length = 10)
	private long ownerPhoneNo;
	@Column(unique = true)
	private String ownerEmailId;
	private String address;
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<User> users;
	
}
