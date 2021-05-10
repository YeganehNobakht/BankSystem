package ir.maktab.service.impl;
import ir.maktab.data.domains.BankTransactions;
import ir.maktab.data.repository.TransactionRepository;
import ir.maktab.dto.AccountDto;
import ir.maktab.dto.TransactionDto;
import ir.maktab.service.interfaces.AccountService;
import ir.maktab.service.interfaces.TransactionService;
import ir.maktab.service.mapper.AccountUserMapper;
import ir.maktab.service.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final AccountUserMapper accountMapper;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountService accountService, AccountUserMapper accountMapper,
                                  TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public void transfer(Long sourceAccountNo, Long desAccountNo, Double amount) throws Exception {
        if (sourceAccountNo==null || desAccountNo == null || amount==null)
            throw new Exception("Input cant be null.");
        Optional<AccountDto> sourceAccount = accountService.getAccountByAccountNumber(sourceAccountNo);
        Optional<AccountDto> destAccount = accountService.getAccountByAccountNumber(desAccountNo);

        if (sourceAccount.isPresent() && destAccount.isPresent()){
            if (sourceAccount.get()!=destAccount.get()) {
                Double destAmount = destAccount.get().getAmount();
                Double sourceAmount = sourceAccount.get().getAmount();
                if (sourceAccount.get().getAmount() > amount) {
                    double desSourceAmount = destAmount + sourceAmount;
                    sourceAccount.get().setAmount(sourceAmount - amount);
                    destAccount.get().setAmount(destAmount + amount);
                    if (sourceAccount.get().getAmount() + destAccount.get().getAmount() == desSourceAmount) {
                        accountService.update(sourceAccount.get());
                        accountService.update(destAccount.get());
                        transactionRepository.saveTransaction(accountMapper.toAccount(sourceAccount.get()),
                                accountMapper.toAccount(destAccount.get()), amount);
                    } else
                        System.out.println("Transfer data is not successfully finished");
                } else {
                    System.out.println("Your account balance is low");
                }
            }else {
                throw new Exception("Source and destination account number is same");
            }
        }

    }

    @Override
    public List<TransactionDto> getTransaction(Date date, Integer max) {
        List<BankTransactions> transactionList = transactionRepository.getTransaction(date,max);
        if (date != null) {
            return transactionList.stream().map(transactionMapper::toTransactionDto).collect(Collectors.toList());
        } else
            return transactionList.stream().map(transactionMapper::toTransactionDto).collect(Collectors.toList());
    }

    @Override
    public void printTransactions(String[] splitLine ) throws ParseException {
        Date fromDate = null;
        Integer max =Integer.valueOf(splitLine[1].trim());
        if (splitLine[0].split(" ").length==2){
            SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            fromDate = formatter2.parse(splitLine[0].split(" ")[1].trim()+" "+splitLine[0].split(" ")[2].trim());
        }
        List<TransactionDto> transaction = getTransaction(fromDate, max);
        transaction.forEach(s-> System.out.println(s.toString()));
    }
}
