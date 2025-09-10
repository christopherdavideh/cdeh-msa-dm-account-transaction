package com.banking.cdeh_msa_dm_account_transaction.service.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionUpdateRequestDto {

    private UUID transactionId;

    private UUID customerId;

    private String sourceAccount;

    @DecimalMin(value = "0.0", inclusive = true, message = "Initial balance debe ser mayor o igual a 0")
    private BigDecimal initialBalance;

    private BigDecimal amount;

    @DecimalMin(value = "0.0", inclusive = true, message = "Available balance debe ser mayor o igual a 0")
    private BigDecimal availableBalance;

    private Boolean transactionStatus;
}
