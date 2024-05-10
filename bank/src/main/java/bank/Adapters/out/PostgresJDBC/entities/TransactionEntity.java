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
    public long id;
    public long amount;
    @Enumerated(EnumType.STRING)
    public CurrencyType currency;
    public LocalDateTime time;
    @Enumerated(EnumType.STRING)
    public TransactionType transactionType;
    public long bankAccountId;
    public long recipientBankAccountId;

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
}
