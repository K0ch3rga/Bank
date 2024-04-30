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
    public long id;
    public long balance;
    public long accountHolder;
    public long bankId;
    @Enumerated(EnumType.STRING)
    public CurrencyType currency;

    public AccountEntity(long id, long balance, long accountHolder, long bankId, CurrencyType currency){
        this.id = id;
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.bankId = bankId;
        this.currency = currency;
    }

    public AccountEntity(){
        this(0, 0, 0, 0, CurrencyType.RUB);
    }
} 