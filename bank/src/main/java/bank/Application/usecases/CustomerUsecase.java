package bank.Application.usecases;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bank.Adapters.out.PostgresJDBC.entities.CustomerEntity;
import bank.Application.dao.CustomerDao;
import bank.Application.dto.NewCustomerDto;
import bank.Domain.Customer;
import bank.Domain.Roles;
import bank.Infrastructure.AccountExistsException;

@Service
public class CustomerUsecase implements UserDetailsService {
    @Autowired
    private CustomerDao customerDao;

    /**
     * Создаёт нового пользователя
     * 
     * @param newCustomer
     * @throws AccountExistsException
     */
    public void saveCustomer(NewCustomerDto newCustomer) throws AccountExistsException {
        customerDao.saveCustomer(newCustomer);
    }

    /**
     * Возращает параметры текущего пользователя
     * из Spring security
     * 
     * @return текущий пользователь
     */
    public Customer getCustomer() {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof UserDetails) {
            var userdetails = (UserDetails) user;
            return customerDao.loadCustomerByEmail(userdetails.getUsername()).get().toRecord();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerEntity> entity = customerDao.loadCustomerByEmail(username);
        if (entity.isPresent()) {
            Customer customer = entity.get().toRecord();
            new CustomerDetails(customer);
            return User.builder()
                    .username(customer.email())
                    .password(customer.password())
                    .authorities(mapRolesToAthorities(customer.roles()))
                    .build();
        }

        throw new UsernameNotFoundException("Customer with email " + username + " not found");
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Roles> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }
}
