package com.banking.cdeh_msa_dm_account_transaction.service.impl;

import com.banking.cdeh_msa_dm_account_transaction.domain.entity.Transaction;
import com.banking.cdeh_msa_dm_account_transaction.exception.ResourceNotFoundException;
import com.banking.cdeh_msa_dm_account_transaction.repository.TransactionRepository;
import com.banking.cdeh_msa_dm_account_transaction.service.TransactionService;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionCreateRequestDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionResponseDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionUpdateRequestDto;
import com.banking.cdeh_msa_dm_account_transaction.service.mapper.TransactionMapper;
import com.banking.cdeh_msa_dm_account_transaction.util.LogMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Mono<TransactionResponseDto> createTransaction(TransactionCreateRequestDto requestDto) {
        Transaction transaction = transactionMapper.toEntity(requestDto);
        return transactionRepository.save(transaction)
                .doFirst(() -> log.info(LogMessages.TRANSACTION_CREATE_REQUEST, requestDto.getCustomerId()))
                .map(transactionMapper::toResponseDto)
                .doOnSuccess(response -> log.info(LogMessages.TRANSACTION_CREATE_SUCCESS, response.getTransactionId()))
                .doOnError(error -> log.error(LogMessages.TRANSACTION_CREATE_ERROR, requestDto.getCustomerId(), error));
    }

    @Override
    public Mono<TransactionResponseDto> getTransactionById(UUID transactionId) {
        return transactionRepository.findById(transactionId)
                .doFirst(() -> log.info(LogMessages.TRANSACTION_GET_REQUEST, transactionId))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                    LogMessages.TRANSACTION_NOT_FOUND.replace("{}", transactionId.toString()))))
                .map(transactionMapper::toResponseDto)
                .doOnSuccess(response -> log.info(LogMessages.TRANSACTION_GET_SUCCESS, transactionId))
                .doOnError(error -> log.error(LogMessages.TRANSACTION_GET_ERROR, transactionId, error));
    }

    @Override
    public Mono<TransactionResponseDto> updateTransaction(UUID transactionId, TransactionUpdateRequestDto requestDto) {
        return transactionRepository.findById(transactionId)
                .doFirst(() -> log.info(LogMessages.TRANSACTION_UPDATE_REQUEST, transactionId))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                    LogMessages.TRANSACTION_NOT_FOUND.replace("{}", transactionId.toString()))))
                .map(existingTransaction -> {
                    transactionMapper.updateEntityFromDto(requestDto, existingTransaction);
                    existingTransaction.setUpdatedAt(LocalDateTime.now());
                    return existingTransaction;
                })
                .flatMap(transactionRepository::save)
                .map(transactionMapper::toResponseDto)
                .doOnSuccess(response -> log.info(LogMessages.TRANSACTION_UPDATE_SUCCESS, transactionId))
                .doOnError(error -> log.error(LogMessages.TRANSACTION_UPDATE_ERROR, transactionId, error));
    }

    @Override
    public Mono<Void> deleteTransaction(UUID transactionId) {
        return transactionRepository.existsById(transactionId)
                .doFirst(() -> log.info(LogMessages.TRANSACTION_DELETE_REQUEST, transactionId))
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new ResourceNotFoundException(
                            LogMessages.TRANSACTION_NOT_FOUND.replace("{}", transactionId.toString())));
                    }
                    return transactionRepository.updateTransactionStatusToInactive(transactionId);
                })
                .then()
                .doOnSuccess(result -> log.info(LogMessages.TRANSACTION_DELETE_SUCCESS, transactionId))
                .doOnError(error -> log.error(LogMessages.TRANSACTION_DELETE_ERROR, transactionId, error));
    }

    @Override
    public Flux<TransactionResponseDto> getTransactionsByCustomerIdAndAccountId(UUID customerId, String accountNumber, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByCustomerIdAndAccountId(customerId, accountNumber, startDate, endDate)
                .doFirst(() -> log.info("Obteniendo transacciones activas para customer: {} y account: {} entre {} y {}", customerId, accountNumber, startDate, endDate))
                .map(transactionMapper::toResponseDto)
                .collectList()
                .doOnSuccess(list -> log.info(LogMessages.TRANSACTION_LIST_SUCCESS, list.size()))
                .doOnError(error -> log.error("Error al obtener transacciones para customer: {} y account: {} entre fechas", customerId, accountNumber, error))
                .flatMapMany(Flux::fromIterable);
    }


    @Override
    public Flux<TransactionResponseDto> getAllActiveTransactions() {
        return transactionRepository.getAllTransactionsActive()
                .doFirst(() -> log.info("Obteniendo todas las transacciones activas"))
                .map(transactionMapper::toResponseDto)
                .collectList()
                .doOnSuccess(list -> log.info(LogMessages.TRANSACTION_LIST_SUCCESS, list.size()))
                .doOnError(error -> log.error("Error al obtener transacciones activas", error))
                .flatMapMany(Flux::fromIterable);
    }
}
