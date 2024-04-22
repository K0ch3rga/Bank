package bank.Adapters.out.PostgresJDBC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "banks")
public class BankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String name;

    public BankEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BankEntity() {
        this(0, "Ошибка");
    }
}
