package ir.maktab.data.repository;

import ir.maktab.data.domains.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void saveAccount(Account account);

    List<Account> cityAccount(String city, Double min, Double max);
    Optional<Account> getAccountByAccountNumber(Long AccountNumber);
    Optional<Account> getAccountByAmount(Double amount);

    void update(Account account);
}
