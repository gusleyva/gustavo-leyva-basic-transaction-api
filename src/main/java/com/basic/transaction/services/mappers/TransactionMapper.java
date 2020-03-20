package com.basic.transaction.services.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.basic.transaction.repositories.entities.Transaction;
import com.basic.transaction.repositories.entities.TransactionAccumulated;
import com.basic.transaction.repositories.entities.TransactionWeeklyReport;
import com.basic.transaction.services.dto.TransactionAccumulatedDto;
import com.basic.transaction.services.dto.TransactionDto;
import com.basic.transaction.services.dto.TransactionWeeklyReportDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
	Transaction toTransactionEntity(TransactionDto source);
	
	List<Transaction> toTransactionEntity(List<TransactionDto> source);
	
	TransactionDto toTransactionDto(Transaction source);
	
	List<TransactionDto> toTransactionDto(List<Transaction> source);
	
	TransactionAccumulatedDto toTransactionAccumulatedDto(TransactionAccumulated source);
	
	List<TransactionWeeklyReportDto> toTransactionWeeklyReportDto(List<TransactionWeeklyReport> source);
}
