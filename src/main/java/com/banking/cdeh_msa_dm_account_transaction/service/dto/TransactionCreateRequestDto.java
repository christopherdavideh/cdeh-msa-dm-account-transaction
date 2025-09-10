package com.banking.cdeh_msa_dm_account_transaction.service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TransactionCreateRequestDto {

    private UUID transactionId;

    private UUID customerId;

    @NotBlank(message = "Source account no puede estar vacío")
    private String sourceAccount;

    @NotNull(message = "Initial balance no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "Initial balance debe ser mayor o igual a 0")
    private BigDecimal initialBalance;

    @NotNull(message = "Amount no puede ser nulo")
    private BigDecimal amount;

    @NotNull(message = "Available balance no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "Available balance debe ser mayor o igual a 0")
    private BigDecimal availableBalance;

    private Boolean transactionStatus;
}
