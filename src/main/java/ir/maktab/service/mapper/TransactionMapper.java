package ir.maktab.service.mapper;

import ir.maktab.data.domains.BankTransactions;
import ir.maktab.dto.TransactionDto;

public interface TransactionMapper {
    TransactionDto toTransactionDto(BankTransactions bankTransactions);
}
