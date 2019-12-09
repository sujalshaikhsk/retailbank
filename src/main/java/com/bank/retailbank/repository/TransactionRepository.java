package com.bank.retailbank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.retailbank.entity.Transaction;

/**
 * @author Vasavi
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
