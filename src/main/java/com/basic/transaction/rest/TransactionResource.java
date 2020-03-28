package com.basic.transaction.rest;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basic.transaction.rest.exceptions.TransactionNotFoundException;
import com.basic.transaction.rest.utils.ResourceUtils;
import com.basic.transaction.service.TransactionService;
import com.basic.transaction.service.interfaces.ITransactionService;
import com.basic.transaction.services.dto.TransactionAccumulatedDto;
import com.basic.transaction.services.dto.TransactionDto;
import com.basic.transaction.services.dto.TransactionReportDto;
import com.basic.transaction.services.dto.TransactionWeeklyReportDto;

@RestController
@RequestMapping(value = "/api/v1/transactions")
public class TransactionResource {
	
	@Autowired
	private ITransactionService transactionService;
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public List<TransactionDto> list(){
		return transactionService.getAll();
	}
	
	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public TransactionDto queryByTransactionId(@PathVariable(name = "id") String id, 
			@RequestParam(name="userId", required = false, defaultValue = "0") Long userId) {
		ResourceUtils.validateRequiredParameter("transaction id", id);
		TransactionDto dto;
		if(userId == null || userId == 0) {
			dto = transactionService.getById(id);
		}else {
			dto = transactionService.getByIdAndUserId(id, userId);
		}
		if(dto == null) {
			throw new TransactionNotFoundException("Transaction not found. "
					+ "\nPlease validate that the transaction id and user id are correct.");
		}
		return dto;
	}
	
	@GetMapping(value = "/user/{userId}", produces = APPLICATION_JSON_VALUE)
	public List<TransactionDto> listByUserId(@PathVariable(name = "userId") Long userId) {
		return transactionService.getByUserId(userId);
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto transaction){
		TransactionDto created = transactionService.create(transaction);
		return new ResponseEntity<>(created, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public TransactionDto update(@PathVariable(name = "id")String id, @RequestBody TransactionDto transaction) {
		ResourceUtils.validateRequiredParameter("transaction id", id);
		return transactionService.update(id, transaction);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name="id")String id){
		ResourceUtils.validateRequiredParameter("transaction id", id);
		transactionService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/accumulated/{userId}", produces = APPLICATION_JSON_VALUE)
	public TransactionAccumulatedDto queryAggreagtedTransactionAmountByUserId(@PathVariable(name = "userId")Long userId) {
		return transactionService.getAccumulatedTransactionAmountByUserIdNoDataBase(userId);
	}
	
	@GetMapping(value = "/weeklyreport/{userId}", produces = APPLICATION_JSON_VALUE)
	public List<TransactionReportDto> queryTransactionWeeklyReport(@PathVariable(name = "userId")Long userId){
		return transactionService.getTransactionWeeklyReportByUserIdNoDatabase(userId);
	}
	
	@GetMapping(value = "/random", produces = APPLICATION_JSON_VALUE)
	public TransactionDto queryRandomTransaction() {
		return transactionService.getRandomTransaction();
	}
}
