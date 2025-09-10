package com.banking.cdeh_msa_dm_account_transaction.service.mapper;

import com.banking.cdeh_msa_dm_account_transaction.domain.entity.Transaction;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionCreateRequestDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionResponseDto;
import com.banking.cdeh_msa_dm_account_transaction.service.dto.TransactionUpdateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TransactionMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "transactionStatus", constant = "true")
    Transaction toEntity(TransactionCreateRequestDto dto);

    TransactionResponseDto toResponseDto(Transaction entity);


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(TransactionUpdateRequestDto dto, @MappingTarget Transaction entity);
}
