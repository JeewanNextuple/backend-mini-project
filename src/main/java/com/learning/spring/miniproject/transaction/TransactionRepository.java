package com.learning.spring.miniproject.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionTracker, Integer> {

}
