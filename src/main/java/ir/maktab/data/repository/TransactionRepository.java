package ir.maktab.data.repository;

import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.BankTransactions;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {

    void saveTransaction(Account sourceAccount, Account destAccount, Double amount);

    List<BankTransactions> getTransaction(Date date,Integer max);
}
