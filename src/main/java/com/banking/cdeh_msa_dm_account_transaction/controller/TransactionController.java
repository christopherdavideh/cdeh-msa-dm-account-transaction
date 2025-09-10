package com.banking.cdeh_msa_dm_account_transaction.controller;

import com.banking.cdeh_msa_dm_account_transaction.service.TransactionService;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionCreateRequestDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionResponseDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;


@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<ResponseEntity<TransactionResponseDto>> createTransaction(
            @Valid @RequestBody TransactionCreateRequestDto requestDto) {
        return transactionService.createTransaction(requestDto)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @GetMapping("/{transactionId}")
    public Mono<ResponseEntity<TransactionResponseDto>> getTransactionById(
            @PathVariable UUID transactionId) {
        return transactionService.getTransactionById(transactionId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{transactionId}")
    public Mono<ResponseEntity<TransactionResponseDto>> updateTransaction(
            @PathVariable UUID transactionId,
            @Valid @RequestBody TransactionUpdateRequestDto requestDto) {
        return transactionService.updateTransaction(transactionId, requestDto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{transactionId}")
    public Mono<ResponseEntity<Void>> deleteTransaction(
            @PathVariable UUID transactionId) {
        return transactionService.deleteTransaction(transactionId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping("/customer/{customerId}/account/{accountNumber}")
    public Flux<TransactionResponseDto> getTransactionsByCustomerIdAndAccountId(
            @PathVariable UUID customerId,
            @PathVariable String accountNumber,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return transactionService.getTransactionsByCustomerIdAndAccountId(customerId, accountNumber, startDate, endDate);
    }

    @GetMapping
    public Flux<TransactionResponseDto> getAllActiveTransactions() {
        return transactionService.getAllActiveTransactions();
    }
}
