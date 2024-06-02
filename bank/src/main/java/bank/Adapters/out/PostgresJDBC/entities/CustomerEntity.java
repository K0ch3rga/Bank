package bank.Adapters.out.PostgresJDBC.entities;

import java.util.Set;

import java.util.HashSet;
import java.util.Arrays;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import bank.Domain.Customer;
import bank.Domain.Roles;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_role", joinColumns = @JoinColumn(name = "customer_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles = new HashSet<>();

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, String phone, String email, String password,
            Roles... roles) {
        this(firstName, lastName, phone, email, password, new HashSet<Roles>(Arrays.asList(roles)));
    }

    public CustomerEntity(String firstName, String lastName, String phone, String email, String password,
            Set<Roles> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static CustomerEntity fromRecord(Customer customer) {
        return new CustomerEntity(customer.firstName(), customer.lastName(), customer.phone(), customer.email(),
                customer.password(), customer.roles());
    }

    public Customer toRecord() {
        return new Customer(id, firstName, lastName, phone, email, password, roles);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
