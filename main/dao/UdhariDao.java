package com.qsp.udharchand_app.dao;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.qsp.udharchand_app.dto.Owner;
import com.qsp.udharchand_app.dto.Transaction;
import com.qsp.udharchand_app.dto.User;
import com.qsp.udharchand_app.dto.Wallet;
import com.qsp.udharchand_app.repository.UdhariRepository;

@Repository
public class UdhariDao {

	@Autowired
	private UdhariRepository repository;
	
	public Owner signUp(Owner owner) {
		owner.getWallet().setWalletAmt(0);
		owner.setWallet(owner.getWallet());
		return repository.save(owner);
	}
	public Object login(long phoneNo,String password) {
		Owner owner=repository.findByOwnerPhoneNo(phoneNo);
		if(owner!=null) {
			if(owner.getPassword().equals(password)) {
				return owner;
			}
			return "InvalidPassword";
		}
		return null;
	}
	public Object addTransaction(long phone,Transaction transaction) {
		Owner owner=repository.findByOwnerPhoneNo(phone);
		if(owner!=null) {
			if(transaction.getAmount()>0) {
				if(transaction.getTransactionType().equalsIgnoreCase("credit")) {
						owner.getWallet().setWalletAmt(owner.getWallet().getWalletAmt()+transaction.getAmount());
						owner.getWallet().getTransactions().add(transaction);
						LocalDate date=LocalDate.now();
						transaction.setDate(String.valueOf(date));
						return repository.save(owner).getWallet().getTransactions();
				}
					if(owner.getWallet().getWalletAmt()>=transaction.getAmount()) {
						owner.getWallet().setWalletAmt(owner.getWallet().getWalletAmt()-transaction.getAmount());
						owner.getWallet().getTransactions().add(transaction);
						LocalDate date=LocalDate.now();
						transaction.setDate(String.valueOf(date));
						return repository.save(owner);
					}
					return "EmptyWallet";
			}
			return "NegativeAmt";
		}
		return null;
	}
	/*this method return me the all transactions present inside database, if there is not any transaction it return me NoSuchTransactionExeption
	 * */
	public List<Transaction> getAllTransactions() {
		return repository.allTransactions();
	}
	public Wallet getAmount(long phone) {
		Owner owner=repository.findByOwnerPhoneNo(phone);
		if(owner!=null) {
			return owner.getWallet();
		}
		return null;
	}
	public Transaction getTransaction(int tId) {
		Transaction transaction=repository.getTransaction(tId);
		if(transaction!=null) {
			return transaction;
		}
		return null;
	}
	//this method return me the list of users
	public boolean addUser(long phone,User user) {
		Owner owner=repository.findByOwnerPhoneNo(phone);
		if(owner!=null) {
			user.setTotalAmt(0);
			owner.getUsers().add(user);
			repository.save(owner);
			return true;
		}
		return false;
	}
}
