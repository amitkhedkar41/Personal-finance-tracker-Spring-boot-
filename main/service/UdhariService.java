package com.qsp.udharchand_app.service;

import java.util.List;

import org.hibernate.query.ResultListTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.udharchand_app.dao.UdhariDao;
import com.qsp.udharchand_app.dto.Owner;
import com.qsp.udharchand_app.dto.Transaction;
import com.qsp.udharchand_app.dto.User;
import com.qsp.udharchand_app.dto.Wallet;
import com.qsp.udharchand_app.exception.EmptyWalletException;
import com.qsp.udharchand_app.exception.InvalidPasswordException;
import com.qsp.udharchand_app.exception.NegativeAmountException;
import com.qsp.udharchand_app.exception.NoSuchTransactionException;
import com.qsp.udharchand_app.exception.UserNotExistException;
import com.qsp.udharchand_app.util.ResponseStructure;

@Service
public class UdhariService {
	
	@Autowired
	private UdhariDao dao;
	
	public ResponseEntity<ResponseStructure<Owner>> signUp(Owner owner) {
		Owner dbOwner=dao.signUp(owner);
		ResponseStructure<Owner> structure=new ResponseStructure<Owner>();
		if(dbOwner!=null) {
			structure.setMessage("user registered!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbOwner);
			return new ResponseEntity<ResponseStructure<Owner>>(structure,HttpStatus.OK);
		}
		return null;
	}
	public ResponseEntity<ResponseStructure<Object>> login(long phone,String password) {
		Object dbOwner=dao.login(phone, password);
		ResponseStructure<Object> structure=new ResponseStructure<Object>();
		if(dbOwner!=null) {
			if(dbOwner instanceof Owner) {
				System.out.println((Owner)dbOwner);
				structure.setMessage("login successfully!");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbOwner);
				return new ResponseEntity<ResponseStructure<Object>>(structure,HttpStatus.FOUND);
			}
			throw new InvalidPasswordException("invalid password");
		}
		throw new UserNotExistException("user not exist!");
	}
	public ResponseEntity<ResponseStructure<Object>> addTransaction(long phone,Transaction transaction) {
		Object dbOwner=dao.addTransaction(phone, transaction);
		ResponseStructure<Object> structure=new ResponseStructure<Object>();
		if(dbOwner!=null) {
			if(dbOwner instanceof Owner) {
				structure.setMessage("Transaction Added successfully!");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbOwner);
				return new ResponseEntity<ResponseStructure<Object>>(structure,HttpStatus.CREATED);
			}
			else if(dbOwner.equals("EmptyWallet")) {
				throw new EmptyWalletException("empty wallet");
			}
			throw new NegativeAmountException("invalid amount!");
		}
		throw new UserNotExistException("user not exist!");
	}
	
	public ResponseEntity<ResponseStructure<List<Transaction>>> getAllTransactions() {
		List<Transaction> transactions=dao.getAllTransactions();
		ResponseStructure<List<Transaction>> structure=new ResponseStructure<List<Transaction>>();
		if(!transactions.isEmpty()) {
			structure.setMessage("data fetchaed!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(transactions);
			return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure,HttpStatus.FOUND);
		}
		structure.setMessage("no transaction exist!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(transactions);
		return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure,HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<ResponseStructure<Wallet>> getWallet(long phone) {
		Wallet wallet=dao.getAmount(phone);
		ResponseStructure<Wallet> structure=new ResponseStructure<Wallet>();
		if(wallet!=null) {
			structure.setMessage("balance fetched!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(wallet);
			return new ResponseEntity<ResponseStructure<Wallet>>(structure,HttpStatus.FOUND);
		}
		throw new UserNotExistException("user not exist!");
	}
	
	public ResponseEntity<ResponseStructure<String>> addUser(long phone,User user) {
		if(dao.addUser(phone, user)) {
			ResponseStructure<String> structure=new ResponseStructure<String>();
				structure.setMessage("balance fetched!");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData("user added successfully!");
				return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.CREATED);
		}
		throw new UserNotExistException("user not found!");
	}
	
	

}
