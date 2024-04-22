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
    public UserDetails loadCustomerByName(String name) {
        Customer customer = customerRepository.findByfirstName(name).toRecord();
        return new org.springframework.security.core.userdetails.User(customer.firstName(), 
        customer.password(), mapRolesToAthorities(customer.roles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Roles> roles)
    {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).toList(); //.collect(Collectors.toList());
    }

    @Override
    public void saveCustomer(NewCustomerDto newCustomer) throws Exception {
        CustomerEntity customerFromDb = customerRepository.findByfirstName(newCustomer.firstName());
        if (customerFromDb != null)
        {
            throw new Exception("customer exist");
        }
        CustomerEntity customerEntity = new CustomerEntity();
        // customer.setActive(true);
        customerRepository.save(customerEntity);
    }
}
