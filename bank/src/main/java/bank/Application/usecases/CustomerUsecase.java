package bank.Application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bank.Application.dao.CustomerDao;
import bank.Application.dto.NewCustomerDto;
import bank.Domain.Customer;
import bank.Infrastructure.AccountExistsException;

@Service
public class CustomerUsecase implements UserDetailsService{
    @Autowired
    private CustomerDao customerDao;

    /**
     * Создаёт нового пользователя 
     * @param newCustomer
     * @throws AccountExistsException
     */
    public void saveCustomer(NewCustomerDto newCustomer) throws AccountExistsException {
        customerDao.saveCustomer(newCustomer);
    }

    /**
     * Возращает параметры текущего пользователя
     * из Spring security
     * @return текущий пользователь
     */
    public Customer getCustomer() {
        var User  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (User instanceof Customer) {
            return (Customer) User;
        }
        return new Customer(0, "null", "null", "null", "null", "null", null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao.loadCustomerByEmail(username);
    }
}
