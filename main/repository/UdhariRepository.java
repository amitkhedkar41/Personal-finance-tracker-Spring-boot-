package com.qsp.udharchand_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.udharchand_app.dto.Owner;
import com.qsp.udharchand_app.dto.Transaction;

public interface UdhariRepository extends JpaRepository<Owner, Integer>{
	
	Owner findByOwnerPhoneNo(long phone);
	
	@Query("select c FROM Transaction c")
	List<Transaction> allTransactions();
	
	@Query("select c FROM Transaction c WHERE c.transactionId=?1")
	Transaction getTransaction(int tId);

}
