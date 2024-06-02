package bank.Adapters.out.PostgresJDBC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import bank.Domain.CurrencyType;

@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long balance;
    private Long accountHolder;
    private Long bankId;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    public AccountEntity(Long id, Long balance, Long accountHolder, Long bankId, CurrencyType currency) {
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.bankId = bankId;
        this.currency = currency;
    }

    public AccountEntity() {
        this(null, null, null, null, CurrencyType.RUB);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(long accountHolder) {
        this.accountHolder = accountHolder;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public CurrencyType getCurrencyType() {
        return currency;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currency = currencyType;
    }
}