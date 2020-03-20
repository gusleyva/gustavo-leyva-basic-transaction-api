package com.basic.transaction.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.transaction.repositories.TransactionRepository;
import com.basic.transaction.repositories.entities.Transaction;
import com.basic.transaction.repositories.entities.TransactionAccumulated;
import com.basic.transaction.repositories.entities.TransactionWeeklyReport;
import com.basic.transaction.service.interfaces.ITransactionService;
import com.basic.transaction.services.dto.TransactionAccumulatedDto;
import com.basic.transaction.services.dto.TransactionDto;
import com.basic.transaction.services.dto.TransactionReportDto;
import com.basic.transaction.services.dto.TransactionWeeklyReportDto;
import com.basic.transaction.services.mappers.TransactionMapper;

@Service
public class TransactionService implements ITransactionService{
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private TransactionMapper mapper;
	
	/**
	 * Get a single transaction record by transactionId
	 * 
	 * @param transactionId
	 * @return TransactionDto
	 */
	public TransactionDto getById(String id) {
		Transaction entity = repository.findById(id).orElse(null);
		TransactionDto dto = mapper.toTransactionDto(entity);
		return dto;
	}
	
	/**
	 * Get transaction records by transactionId and userId
	 * 
	 * @param transactionId
	 * @param userId
	 * @return TransactionDto Object
	 */
	public TransactionDto getByIdAndUserId(String id, Long userId) {
		Transaction entity = repository.findByTransactionAndUserId(id, userId);
		TransactionDto dto = mapper.toTransactionDto(entity);
		return dto;
	}
	
	/**
	 * Get all transaction records by userId
	 * 
	 * @param userId
	 * @return TransactionDto List
	 */
	public List<TransactionDto> getByUserId(Long userId) {
		List<Transaction> transactions = repository.findTransactionsByUserId(userId);
		return mapper.toTransactionDto(transactions);
	}
	
	/**
	 * Get all transaction records
	 * 
	 * @return TransactionDto List
	 */
	public List<TransactionDto> getAll(){
		List<Transaction> entities = repository.findAll();
		List<TransactionDto> dtos = mapper.toTransactionDto(entities);
		return dtos;
	}
	
	/**
	 * Create a Transaction record
	 * 
	 * @return TransactionDto object
	 */
	public TransactionDto create(TransactionDto dto) {
		if(dto.getId() != null) {
			return null;
		}
		
		Transaction entity = mapper.toTransactionEntity(dto);
		entity.setId(getTransactionId());
		Transaction created = repository.save(entity);
		TransactionDto createdDto = mapper.toTransactionDto(created);
		return createdDto;
	}
	
	/**
	 * Update a Transaction record
	 * 
	 * @return TransactionDto object
	 */
	public TransactionDto update(String id, TransactionDto dto) {
		if(!repository.existsById(id) || !dto.getId().equals(id)) {
			return null;
		}
		
		Transaction entity = mapper.toTransactionEntity(dto);
		Transaction updated = repository.save(entity);
		TransactionDto updatedDto =  mapper.toTransactionDto(updated);
		return updatedDto;
	}
	
	/**
	 * Deletes a Transaction record
	 * 
	 * @param transactionId
	 * @return void
	 */
	public void delete(String id) {
		repository.deleteById(id);
	}
	
	/**
	 * Services method to get the sum amounts for all transactions by user
	 * 
	 * @param userId
	 * @return TransactionAccumulatedDto
	 */
	public TransactionAccumulatedDto getAccumulatedTransactionAmountByUserId(Long userId) {
		TransactionAccumulated entity = repository.getAccumulatedTransactionAmounts(userId);
		TransactionAccumulatedDto dto = mapper.toTransactionAccumulatedDto(entity);
		return dto;
	}
	
	/**
	 * Services method to get the sum amounts for all transactions by user
	 * 
	 * @param userId
	 * @return TransactionAccumulatedDto
	 */
	public TransactionAccumulatedDto getAccumulatedTransactionAmountByUserIdNoDataBase(Long userId) {
		List<TransactionDto> listTransactionDto = getByUserId(userId); 
		return getAccumulatedAmount(userId, listTransactionDto);
	}
	
	/**
	 * Iterates on the list of transactions by user
	 * and do the sum amount of each transaction
	 * 
	 * @param userId
	 * @param listTransactionDto
	 * @return TransactionAccumulatedDto Object
	 */
	protected TransactionAccumulatedDto getAccumulatedAmount(Long userId, List<TransactionDto> listTransactionDto) {
		TransactionAccumulatedDto transactionAccumulatedDto = new TransactionAccumulatedDto();
		double accumulatedAmount = 0.0;
		if(listTransactionDto != null) {
			accumulatedAmount = listTransactionDto.stream()
					.mapToDouble(transactionDto -> transactionDto.getAmount())
					.sum();
		}
		transactionAccumulatedDto.setUserId(userId);
		transactionAccumulatedDto.setAmount(accumulatedAmount);
		
		return transactionAccumulatedDto;
	}
	
	public List<TransactionWeeklyReportDto> getTransactionWeeklyReportByUserId(Long userId) {
		List<TransactionWeeklyReport> transactionWeeklyReport = repository.getTransactionWeeklyReport(userId);
		return mapper.toTransactionWeeklyReportDto(transactionWeeklyReport);
	}
	
	public List<TransactionReportDto> getTransactionWeeklyReportByUserIdNoDatabase(Long userId) {
		List<TransactionDto> listTransactionDto = getByUserId(userId);
		return getTransactionWeeklyReport(userId, listTransactionDto);
	}
	
	protected List<TransactionReportDto> getTransactionWeeklyReport(Long userId, List<TransactionDto> listTransactionDto){
		//TODO
		List<TransactionReportDto> transactionReportDtoList = new ArrayList<>();
		for(TransactionDto transactionDto: listTransactionDto) {
			TransactionReportDto transactionReportDto = new TransactionReportDto();
			//setid
			//setWeekStart
			//setWeekFinish
			//setQuantity
			//setAmount
			//setTotalAmount
			transactionReportDtoList.add(transactionReportDto);
		}
		
		return null;
	}
	
	/**
	 * Get random transaction record based on the number of records and current time
	 * 
	 * @return TransactionDto object
	 */
	public TransactionDto getRandomTransaction() {
		List<TransactionDto> listTransactionsDtos = getAll();
		int max = listTransactionsDtos.size() - 1;
		int randomPosition = (int)(System.currentTimeMillis() % max);
		return listTransactionsDtos.get(randomPosition);
	}
	
	/**
	 * Generated transaction id using UUID
	 * Format: 22994424-9eaf-417f-82d6-e57f93777dc4
	 * @return Transaction id
	 */
	protected String getTransactionId() {
		StringBuilder transactionId = new StringBuilder();
		
		String currentDate = String.valueOf(new Date().getTime());
		transactionId.append(currentDate.substring(currentDate.length() - 8));
		
		String uuid = UUID.randomUUID().toString();
		String[] arrayUuId = uuid.split("-");
		for(String sectionUuId: arrayUuId) {
			transactionId.append("-").append(sectionUuId);
		}
		return transactionId.toString();
	}
}
