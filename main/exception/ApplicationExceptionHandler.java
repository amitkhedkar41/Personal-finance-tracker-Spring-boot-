package com.qsp.udharchand_app.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.udharchand_app.dto.Owner;
import com.qsp.udharchand_app.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> handleConstraintViolationException(ConstraintViolationException e) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("owner already exist");
		structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		structure.setData("already exist");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_ACCEPTABLE);
	}
	

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidPasswordException(InvalidPasswordException e) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("invalid password!");
		structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		structure.setData("invalid password please Enter correct password!");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(UserNotExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserNotExistException(UserNotExistException e) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("user Not Exist!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("user Not Exist please register!");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NegativeAmountException.class)
	public ResponseEntity<ResponseStructure<String>> handleNegativeAmountException(NegativeAmountException e) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("amout should not be 0 or negative!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("invalid amt");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoSuchTransactionException.class)
	public ResponseEntity<ResponseStructure<String>> handleNoSuchTransactionException(NoSuchTransactionException e) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("no transaction found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("no such transaction");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmptyWalletException.class)
	public ResponseEntity<ResponseStructure<String>> handleEmptyWalletException(EmptyWalletException e) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("wallet is empty ! cant be perform debit operation!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("no such transaction");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}

}
