package com.banking.cdeh_msa_dm_account_transaction.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("transactions")
public class Transaction {

    @Id
    @Column("transaction_id")
    private UUID transactionId;

    @Column("customer_id")
    private UUID customerId;

    @Column("source_account")
    private String sourceAccount;

    @Column("initial_balance")
    private BigDecimal initialBalance;

    @Column("amount")
    private BigDecimal amount;

    @Column("available_balance")
    private BigDecimal availableBalance;

    @Column("transaction_status")
    private Boolean transactionStatus;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
