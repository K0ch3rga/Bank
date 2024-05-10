package bank.Adapters.out.PostgresJDBC;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import bank.Adapters.out.PostgresJDBC.entities.CustomerEntity;
import bank.Adapters.out.PostgresJDBC.repositories.CustomerRepository;
import bank.Application.dao.CustomerDao;
import bank.Application.dto.NewCustomerDto;
import bank.Domain.Customer;
import bank.Domain.Roles;

@Service
public class CustomerDaoAdapter implements CustomerDao {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public synchronized UserDetails loadCustomerByName(String email) {
        Customer customer = customerRepository.findByEmail(email).toRecord();
        return new org.springframework.security.core.userdetails.User(customer.firstName(),
                customer.password(), mapRolesToAthorities(customer.roles()));
    }

    private synchronized List<? extends GrantedAuthority> mapRolesToAthorities(Set<Roles> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).toList(); // .collect(Collectors.toList());
    }

    @Override
    public synchronized void saveCustomer(NewCustomerDto newCustomer) throws Exception {
        CustomerEntity customerFromDb = customerRepository.findByFirstName(newCustomer.firstName());
        if (customerFromDb != null) {
            throw new Exception("customer exist");
        }
        CustomerEntity customerEntity = new CustomerEntity(newCustomer.firstName(), newCustomer.lastName(),
                newCustomer.phone(), newCustomer.email(), newCustomer.password(), Roles.CUSTOMER);
        // customer.setActive(true);
        System.out.println(customerEntity);
        customerRepository.save(customerEntity);
    }
}
