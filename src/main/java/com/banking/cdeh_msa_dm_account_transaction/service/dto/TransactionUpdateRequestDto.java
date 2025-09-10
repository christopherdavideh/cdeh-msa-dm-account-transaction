package com.banking.cdeh_msa_dm_account_transaction.service.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionUpdateRequestDto {

    private String customerId;

    private String sourceAccount;

    @DecimalMin(value = "0.0", inclusive = true, message = "Initial balance debe ser mayor o igual a 0")
    private BigDecimal initialBalance;

    @DecimalMin(value = "0.01", message = "Amount debe ser mayor a 0")
    private BigDecimal amount;

    @DecimalMin(value = "0.0", inclusive = true, message = "Available balance debe ser mayor o igual a 0")
    private BigDecimal availableBalance;

    private Boolean transactionStatus;
}
