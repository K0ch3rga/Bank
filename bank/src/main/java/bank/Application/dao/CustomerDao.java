package bank.Application.dao;

import org.springframework.security.core.userdetails.UserDetails;

import bank.Application.dto.NewCustomerDto;
import bank.Domain.Customer;
import bank.Infrastructure.AccountExistsException;

public interface CustomerDao {
    // UserDetails loadCustomerByEmail(String email);
    Customer loadCustomerByEmail(String email);
    void saveCustomer(NewCustomerDto newCustomer) throws AccountExistsException;
}
