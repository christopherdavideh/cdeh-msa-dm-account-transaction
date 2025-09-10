package com.banking.cdeh_msa_dm_account_transaction.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    private UUID transactionId;
    private String customerId;
    private String sourceAccount;
    private BigDecimal initialBalance;
    private BigDecimal amount;
    private BigDecimal availableBalance;
    private Boolean transactionStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
