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
    public Long id;
    public Long balance;
    public Long accountHolder;
    public Long bankId;
    @Enumerated(EnumType.STRING)
    public CurrencyType currency;

    public AccountEntity(Long id, Long balance, Long accountHolder, Long bankId, CurrencyType currency){
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.bankId = bankId;
        this.currency = currency;
    }

    public AccountEntity(){
        this(null, null, null, null, CurrencyType.RUB);
    }
} 