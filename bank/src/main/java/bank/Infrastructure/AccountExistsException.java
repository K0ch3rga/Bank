package bank.Infrastructure;

import bank.Domain.Customer;

public class AccountExistsException extends RuntimeException {
    public AccountExistsException(Customer customer) {
        super("Account with params" + customer.email() + "already exist");
    }
}
