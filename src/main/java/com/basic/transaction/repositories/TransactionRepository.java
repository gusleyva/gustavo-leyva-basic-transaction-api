package com.basic.transaction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.basic.transaction.repositories.entities.Transaction;
import com.basic.transaction.repositories.entities.TransactionAccumulated;
import com.basic.transaction.repositories.entities.TransactionWeeklyReport;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{

	@Query(value = "SELECT t FROM Transaction t WHERE t.userId = :userId ORDER BY created ASC")
	List<Transaction> findTransactionsByUserId(@Param("userId")Long userId);
	
	@Query(value = "SELECT t FROM Transaction t WHERE t.id = :id AND t.userId = :userId ORDER BY created ASC")
	Transaction findByTransactionAndUserId(@Param("id")String id, @Param("userId")Long userId);
	
	@Query(value = "SELECT new com.basic.transaction.repositories.entities.TransactionAccumulated(t.userId, sum(t.amount)) from Transaction t WHERE t.userId = :userId GROUP BY t.userId")
	TransactionAccumulated getAccumulatedTransactionAmounts(@Param("userId")Long userId);
	
	@Query(value = "select new com.basic.transaction.repositories.entities.TransactionWeeklyReport(t.userId, sum(t.amount), count(t.amount), week(t.created)) FROM Transaction t WHERE t.userId = :userId GROUP BY t.userId, week(t.created)")
	List<TransactionWeeklyReport> getTransactionWeeklyReport(@Param("userId")Long userId);
}
