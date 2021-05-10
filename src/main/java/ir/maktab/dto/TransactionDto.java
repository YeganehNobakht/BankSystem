package ir.maktab.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class TransactionDto {
    private Long sourceAccountNo;
    private Long destinationAccountNo;
    private Double amount;
    private Date transferDate;

    public Long getSourceAccountNo() {
        return sourceAccountNo;
    }

    public TransactionDto setSourceAccountNo(Long sourceAccountNo) {
        this.sourceAccountNo = sourceAccountNo;
        return this;
    }

    public Long getDestinationAccountNo() {
        return destinationAccountNo;
    }

    public TransactionDto setDestinationAccountNo(Long destinationAccountNo) {
        this.destinationAccountNo = destinationAccountNo;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionDto setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public TransactionDto setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
        return this;
    }

    @Override
    public String toString() {
        return
                "sourceAccountNo=" + sourceAccountNo +
                ", destinationAccountNo=" + destinationAccountNo +
                ", amount=" + amount +
                ", transferDate=" + transferDate +
                '}';
    }
}
