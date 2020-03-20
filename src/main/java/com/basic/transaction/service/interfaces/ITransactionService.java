package com.basic.transaction.service.interfaces;

import java.util.List;

import com.basic.transaction.services.dto.TransactionAccumulatedDto;
import com.basic.transaction.services.dto.TransactionDto;
import com.basic.transaction.services.dto.TransactionReportDto;
import com.basic.transaction.services.dto.TransactionWeeklyReportDto;

public interface ITransactionService {

	/**
	 * Get a single transaction record by transactionId
	 * 
	 * @param transactionId
	 * @return TransactionDto
	 */
	TransactionDto getById(String id);
	
	/**
	 * Get transaction records by transactionId and userId
	 * 
	 * @param transactionId
	 * @param userId
	 * @return TransactionDto Object
	 */
	TransactionDto getByIdAndUserId(String id, Long userId);
	
	/**
	 * Get all transaction records by userId
	 * 
	 * @param userId
	 * @return TransactionDto List
	 */
	List<TransactionDto> getByUserId(Long userId);
	
	/**
	 * Get all transaction records
	 * 
	 * @return TransactionDto List
	 */
	List<TransactionDto> getAll();
	
	/**
	 * Create a Transaction record
	 * 
	 * @return TransactionDto object
	 */
	TransactionDto create(TransactionDto dto);
	
	/**
	 * Update a Transaction record
	 * 
	 * @return TransactionDto object
	 */
	TransactionDto update(String id, TransactionDto dto);
	
	/**
	 * Deletes a Transaction record
	 * 
	 * @return void
	 */
	void delete(String id);
	
	/**
	 * Services method to get the sum amounts for all transactions by user
	 * 
	 * @param userId
	 * @return TransactionAccumulatedDto
	 */
	TransactionAccumulatedDto getAccumulatedTransactionAmountByUserId(Long userId);
	
	/**
	 * Iterates on the list of transactions by user
	 * and do the sum amount of each transaction
	 * 
	 * @param userId
	 * @param listTransactionDto
	 * @return TransactionAccumulatedDto Object
	 */
	TransactionAccumulatedDto getAccumulatedTransactionAmountByUserIdNoDataBase(Long userId);
	
	
	List<TransactionWeeklyReportDto> getTransactionWeeklyReportByUserId(Long userId);
	
	List<TransactionReportDto> getTransactionWeeklyReportByUserIdNoDatabase(Long userId);
	
	/**
	 * Get random transaction record based on the number of records and current time
	 * 
	 * @return TransactionDto object
	 */
	TransactionDto getRandomTransaction();
}
