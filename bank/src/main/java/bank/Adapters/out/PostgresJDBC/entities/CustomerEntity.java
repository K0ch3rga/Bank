package bank.Adapters.out.PostgresJDBC.entities;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import bank.Domain.Customer;
import bank.Domain.Roles;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_role", joinColumns = @JoinColumn(name = "customer_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles = new HashSet<>();

    String firstName;
    String lastName;
    String phone;
    String email;
    String password;

    public CustomerEntity() {
        this("Bob", "Bob");
    }

    public CustomerEntity(String firstName, String lastName, Roles... roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        var set = new HashSet<Roles>();
        Collections.addAll(set, roles);
        this.roles = set;
        // TODO Add other fields
    }

    public CustomerEntity(String firstName, String lastName, Set<Roles> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public static CustomerEntity fromRecord(Customer customer) {
        return new CustomerEntity(customer.firstName(), customer.lastName(), customer.roles());
    }

    public Customer toRecord() {
        return new Customer(id, firstName, lastName, phone, email, password, roles);
    }

}
