package bank.Application.dao;

import org.springframework.security.core.userdetails.UserDetails;

import bank.Application.dto.NewCustomerDto;

public interface CustomerDao {
    UserDetails loadCustomerByName(String name);
    void saveCustomer(NewCustomerDto newCustomer) throws Exception;
}
