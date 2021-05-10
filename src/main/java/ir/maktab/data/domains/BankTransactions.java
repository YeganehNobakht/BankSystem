package ir.maktab.data.domains;

import javax.persistence.*;
import java.util.Date;
@Entity
public class BankTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transId;
   // @Convert(converter = Account.class)
   @ManyToOne
   @AttributeOverrides({
           @AttributeOverride(name = "id" , column = @Column(name="source_Id"))
   })

   @JoinColumn(name="source", nullable=false)
    private Account sourceAccount;
   // @Convert(converter = Account.class)
   @AttributeOverrides({
           @AttributeOverride(name = "id" , column = @Column(name="des_Id"))
   })
   @ManyToOne
   @JoinColumn(name="destination", nullable=false)
    private Account destinationAccount;
    private Double amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferDate;


    public Long getTransId() {
        return transId;
    }

    public BankTransactions setTransId(Long id) {
        this.transId = id;
        return this;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public BankTransactions setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public BankTransactions setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
        return this;
    }
}
