package bank.Application.dao;

import java.util.Optional;

import bank.Adapters.out.PostgresJDBC.entities.CustomerEntity;
import bank.Application.dto.NewCustomerDto;
import bank.Infrastructure.AccountDoesntExistExeption;
import bank.Infrastructure.AccountExistsException;


public interface CustomerDao {
    // UserDetails loadCustomerByEmail(String email);
    Optional<CustomerEntity> loadCustomerByEmail(String email) throws AccountDoesntExistExeption;
    void saveCustomer(NewCustomerDto newCustomer) throws AccountExistsException;
}
