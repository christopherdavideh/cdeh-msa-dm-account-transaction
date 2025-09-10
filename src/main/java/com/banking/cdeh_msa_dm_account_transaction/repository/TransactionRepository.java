package com.banking.cdeh_msa_dm_account_transaction.repository;

import com.banking.cdeh_msa_dm_account_transaction.domain.entity.Transaction;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface TransactionRepository extends R2dbcRepository<Transaction, UUID> {

    @Query("SELECT * FROM transactions WHERE customer_id = :customerId AND source_account = :accountNumber AND transaction_status = true AND created_at BETWEEN :startDate AND :endDate ORDER BY created_at DESC")
    Flux<Transaction> findByCustomerIdAndAccountId(UUID customerId, String accountNumber, LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT * FROM transactions WHERE transaction_status = true ORDER BY created_at DESC")
    Flux<Transaction> getAllTransactionsActive();

    Flux<Transaction> findByCustomerIdAndTransactionStatus(UUID customerId, Boolean status);

    @Modifying
    @Query("UPDATE transactions SET transaction_status = false WHERE transaction_id = :transactionId")
    Mono<Integer> updateTransactionStatusToInactive(UUID transactionId);
}
