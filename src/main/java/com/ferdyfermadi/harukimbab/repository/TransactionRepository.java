package com.ferdyfermadi.harukimbab.repository;

import com.ferdyfermadi.harukimbab.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
