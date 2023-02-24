package com.learning.spring.miniproject.transaction;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepo;

	@GetMapping("/orders")
	public List<TransactionTracker> retreiveAllOrders() {
		List<TransactionTracker> trackedOrders = transactionRepo.findAll();
		return trackedOrders;
	}
}
