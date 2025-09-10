package com.banking.cdeh_msa_dm_account_transaction.service;

import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionCreateRequestDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionResponseDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionUpdateRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionService {

    Mono<TransactionResponseDto> createTransaction(TransactionCreateRequestDto requestDto);

    Mono<TransactionResponseDto> getTransactionById(UUID transactionId);

    Mono<TransactionResponseDto> updateTransaction(UUID transactionId, TransactionUpdateRequestDto requestDto);

    Mono<Void> deleteTransaction(UUID transactionId);

    Flux<TransactionResponseDto> getTransactionsByCustomerIdAndAccountId(String customerId, String accountId, LocalDateTime startDate, LocalDateTime endDate);


    Flux<TransactionResponseDto> getAllActiveTransactions();
}
