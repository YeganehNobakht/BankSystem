package ir.maktab.service.mapper;

import ir.maktab.data.domains.BankTransactions;
import ir.maktab.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public TransactionDto toTransactionDto(BankTransactions bankTransactions) {
        return new TransactionDto().setAmount(bankTransactions.getAmount())
                .setDestinationAccountNo(bankTransactions.getDestinationAccount().getAccountNumber())
                .setSourceAccountNo(bankTransactions.getSourceAccount().getAccountNumber())
                .setTransferDate(bankTransactions.getTransferDate());
    }
}
