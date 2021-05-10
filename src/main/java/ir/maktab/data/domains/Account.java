package ir.maktab.data.domains;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(100)")
    @Enumerated
    private AccountType accountType;

    private Double amount;

    private Long password;
    @Column(unique = true)
    private Long accountNumber;

    @ManyToOne
    @JoinColumn(name="emailAddress", nullable=false)
    private User user;

   @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "sourceAccount")
   private List<BankTransactions> transactionForSourceAcc = new ArrayList<>();

    public List<BankTransactions> getTransactionForSourceAcc() {
        return transactionForSourceAcc;
    }

    public Account setTransactionForSourceAcc(List<BankTransactions> transactionForSourceAcc) {
        this.transactionForSourceAcc = transactionForSourceAcc;
        return this;
    }

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "destinationAccount")
   private List<BankTransactions> transactionForDesAcc = new ArrayList<>();

    public List<BankTransactions> getTransactionForDesAcc() {
        return transactionForDesAcc;
    }

    public Account setTransactionForDesAcc(List<BankTransactions> transactionForDesAcc) {
        this.transactionForDesAcc = transactionForDesAcc;
        return this;
    }

    public Account() {

    }

    public Integer getId() {
        return id;
    }

    public Account setId(Integer id) {
        this.id = id;
        return this;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Account setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Account setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Account setUser(User user) {
        this.user = user;
        return this;
    }

    public Long getPassword() {
        return password;
    }

    public Account setPassword(Long password) {
        this.password = password;
        return this;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Account setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }
}
