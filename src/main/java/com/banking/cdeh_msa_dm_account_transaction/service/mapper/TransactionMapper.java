package com.banking.cdeh_msa_dm_account_transaction.service.mapper;

import com.banking.cdeh_msa_dm_account_transaction.domain.entity.Transaction;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionCreateRequestDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionResponseDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionUpdateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper for Transaction entity and DTOs
 */
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TransactionMapper {

    /**
     * Map TransactionCreateRequestDto to Transaction entity
     */
    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Transaction toEntity(TransactionCreateRequestDto dto);

    /**
     * Map Transaction entity to TransactionResponseDto
     */
    TransactionResponseDto toResponseDto(Transaction entity);

    /**
     * Update existing Transaction entity with TransactionUpdateRequestDto
     */
    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(TransactionUpdateRequestDto dto, @MappingTarget Transaction entity);
}
