package bank.Adapters.out.PostgresJDBC.entities;

import java.time.LocalDateTime;

import bank.Domain.CurrencyType;
import bank.Domain.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue
    private long id;
    private long amount;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private long bankAccountId;
    private long recipientBankAccountId;

    public TransactionEntity(long id, long amount, CurrencyType currency, LocalDateTime time,
            TransactionType transactionType, long bankAccountId, long recipientBankAccountId) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.time = time;
        this.transactionType = transactionType;
        this.bankAccountId = bankAccountId;
        this.recipientBankAccountId = recipientBankAccountId;
    }

    public TransactionEntity() {
        this(0, 0, CurrencyType.RUB, LocalDateTime.now(), TransactionType.TRANSFER, 0, 0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public long getRecipientBankAccountId() {
        return recipientBankAccountId;
    }

    public void setRecipientBankAccountId(long recipientBankAccountId) {
        this.recipientBankAccountId = recipientBankAccountId;
    }

}
