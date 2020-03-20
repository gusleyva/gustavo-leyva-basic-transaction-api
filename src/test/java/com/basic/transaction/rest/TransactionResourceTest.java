package com.basic.transaction.rest;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.basic.transaction.services.dto.TransactionDto;

import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class TransactionResourceTest {

	@Autowired
    private TransactionResource transactionResource;

	@Test
	public void basicMessageTest(){
		List<TransactionDto> lisTransactionDto = transactionResource.list();
		assertTrue(lisTransactionDto.size() > 0);
	}
	
	@Test(expected = com.basic.transaction.rest.exceptions.TransactionNotFoundException.class)
	public void queryByTransactionIdUserIdNullErrorTest() {
		transactionResource.queryByTransactionId("dummyId", 0L);
	}
	
}
