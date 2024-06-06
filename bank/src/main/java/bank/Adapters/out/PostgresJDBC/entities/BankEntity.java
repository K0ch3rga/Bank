package bank.Adapters.out.PostgresJDBC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "banks")
public class BankEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public BankEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BankEntity() {
        this(0, "Ошибка");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
