package com.qsp.udharchand_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.udharchand_app.dto.Owner;
import com.qsp.udharchand_app.dto.Transaction;
import com.qsp.udharchand_app.dto.User;
import com.qsp.udharchand_app.dto.Wallet;
import com.qsp.udharchand_app.service.UdhariService;
import com.qsp.udharchand_app.util.ResponseStructure;

@RestController
public class UdhariController {
	
	@Autowired
	private UdhariService service;
	
	@PostMapping("/signup/owner")
	public ResponseEntity<ResponseStructure<Owner>> signUp(@RequestBody Owner owner) {
		return service.signUp(owner);
	}
	@GetMapping("/login/owner/{phone}/{password}")
	public ResponseEntity<ResponseStructure<Object>> login(@PathVariable long phone,@PathVariable String password) {
		return service.login(phone, password);
	}
	@PutMapping("/transaction/wallet/{phone}")
	public ResponseEntity<ResponseStructure<Object>> addTransaction(@PathVariable long phone,@RequestBody Transaction transaction ) {
		return service.addTransaction(phone, transaction);
	}
	@GetMapping("/all/transactions")
	public ResponseEntity<ResponseStructure<List<Transaction>>> getAllTransaction() {
		return service.getAllTransactions();
	}
	@GetMapping("/wallet/{phone}")
	public ResponseEntity<ResponseStructure<Wallet>> getWallet(@PathVariable long phone) {
		return service.getWallet(phone);
	}
	@PutMapping("/add/user/{phone}")
	public ResponseEntity<ResponseStructure<String>> getWallet(@PathVariable long phone,@RequestBody User user) {
		return service.addUser(phone, user);
	}

}
