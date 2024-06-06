package bank.Adapters.out.PostgresJDBC;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.Adapters.out.PostgresJDBC.entities.CustomerEntity;
import bank.Adapters.out.PostgresJDBC.repositories.CustomerRepository;
import bank.Application.dao.CustomerDao;
import bank.Application.dto.NewCustomerDto;
import bank.Domain.Roles;
import bank.Infrastructure.AccountExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerDaoAdapter implements CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerDaoAdapter()
    {}


    @Override
    @Transactional
    public synchronized Optional<CustomerEntity> loadCustomerByEmail(String email) {
            return customerRepository.findByEmail(email);
            
        // return new org.springframework.security.core.userdetails.User(customer.email(),
        //         customer.password(), mapRolesToAthorities(customer.roles()));
    }

    @Override
    @Transactional
    public synchronized void saveCustomer(NewCustomerDto newCustomer) throws AccountExistsException {
        Optional<CustomerEntity> customerFromDb = customerRepository.findByEmail(newCustomer.email());
        if (customerFromDb.isPresent()) {
            throw new AccountExistsException(customerFromDb.get().toRecord());
        }
        CustomerEntity customerEntity = new CustomerEntity(newCustomer.firstName(), newCustomer.lastName(),
                newCustomer.phone(), newCustomer.email(), newCustomer.password(), Roles.CUSTOMER);
        System.out.println("Saved " + newCustomer.password());
        System.out.println("Saved " + customerEntity);
        customerRepository.save(customerEntity);
    }

}
