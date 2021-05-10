package ir.maktab.service.impl;

import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.AccountType;
import ir.maktab.data.domains.User;
import ir.maktab.data.repository.AccountRepository;
import ir.maktab.dto.AccountDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.interfaces.AccountService;
import ir.maktab.service.interfaces.GeneratorService;
import ir.maktab.service.interfaces.UserService;
import ir.maktab.service.mapper.AccountUserMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
//    private final AccountMapper accountMapper;
    private final AccountUserMapper accountUserMapper;
    private final AccountRepository accountRepository;
    private final GeneratorService generate4Digit;
    private final GeneratorService generate13Digit;
    private final UserService userService;

    public AccountServiceImpl(AccountUserMapper accountUserMapper,
                              AccountRepository accountRepository,
                              @Qualifier("generate4digitServiceImpl") GeneratorService generate4Digit,
                              @Qualifier("generate13digitServiceImpl") GeneratorService generate13Digit,
                              UserService userService) {
        this.accountUserMapper = accountUserMapper;
        this.accountRepository = accountRepository;
        this.generate4Digit = generate4Digit;
        this.generate13Digit = generate13Digit;
        this.userService = userService;
    }

    @Override
    public AccountType getAccountType(String input) {
        switch (input.toUpperCase()){
            case "CURRENT":
                return AccountType.CURRENT_ACCOUNT;
             case "SHORT":
                return AccountType.SHORT_TERM_ACCOUNT;
             case "LONG":
                return AccountType.LONG_TERN_ACCOUNT;
        }
        System.out.println("Invalid Account type");
        return null;
    }

    @Override
    public void saveAccount(AccountDto accountDto) {
        Account account = accountUserMapper.toAccount(accountDto);
        accountRepository.saveAccount(account);

    }
    @Override
    public List<AccountDto> cityAccount(String city, Double min, Double max) throws Exception {
        if (city==null)
            throw new Exception("City can not be null");
        List<Account> accounts = accountRepository.cityAccount(city, min, max);
        return accounts.stream().map(accountUserMapper::toAccountDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDto> getAccountByAccountNumber(Long AccountNumber) throws Exception {
        Optional<Account> account = accountRepository.getAccountByAccountNumber(AccountNumber);
        if (account.isPresent()) {
            return Optional.of(accountUserMapper.toAccountDto(account.get()));
        }
        throw new Exception("Account not found");
    }

    @Override
    public Optional<AccountDto> getAccountByAmount(Double amount) {
        return Optional.empty();
    }

    @Override
    public void update(AccountDto accountDto) throws Exception {
        if (Objects.isNull(accountDto.getAccountNumber()))
            throw new Exception("id is null");

        Account account = accountUserMapper.toAccount(accountDto);
        accountRepository.update(account);
    }

    @Override
    public void createAccountsForUser(String[] splitLine , AccountDto accountDto, UserDto user) throws Exception {
        Long pass = generate4Digit.crateRandomNumber();
        Long accountNumber = generate13Digit.crateRandomNumber();
        accountDto.setUserDto(user).setPassword(pass).setAccountNumber(accountNumber)
                .setAccountType(getAccountType(splitLine[2].trim()))
                .setAmount(Double.valueOf(splitLine[1].trim()));
        user.getAccounts().add(accountDto);
        userService.update(user);
        saveAccount(accountDto);
        System.out.println("Your account is successfully created.");
        System.out.println("Password: "+pass+"\nAccount Number: "+ accountNumber);
    }

    @Override
    public void printAccounts(String[] splitLine) throws Exception {
        String city = splitLine[0].split(" ")[1].trim();
        Double min = null;
        Double max = null;
        if (splitLine.length==3){
            min = Double.valueOf(splitLine[1].trim());
            max = Double.valueOf(splitLine[2].trim());
        }
        List<AccountDto> accountDtoList = cityAccount(city, min, max);
        accountDtoList.forEach(a-> System.out.println(a.toString()));
    }


}
