package bank.Adapters.out.PostgresJDBC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import bank.Domain.CurrencyType;

@Entity
@Table(name = "entities")
public record AccountEntity(
    @Id
    @GeneratedValue
    long id,
    long balance,
    long accountHolder,
    long bankId,
    CurrencyType currency
) {
    public AccountEntity(){
        this(0, 0, 0, 0, CurrencyType.RUB);
    }
} 