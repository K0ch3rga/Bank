package bank.Application.usecases;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        System.out.println(user);
        System.out.println(user instanceof UserDetails);
        if (user instanceof UserDetails) {
            var userdetails = (UserDetails)user;
            return customerDao.loadCustomerByEmail(userdetails.getUsername());
        }
        return new Customer(0, "null", "null", "null", "null", "null", null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerDao.loadCustomerByEmail(username);
        return new org.springframework.security.core.userdetails.User(customer.email(), customer.password(),
        mapRolesToAthorities(customer.roles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Roles> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }
}
