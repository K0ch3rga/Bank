package bank.Application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.Application.dao.CustomerDao;
import bank.Application.dto.NewCustomerDto;

@Service
public class CustomerUsecase {
    @Autowired
    private CustomerDao customerDao;

    public void saveCustomer(NewCustomerDto newCustomer) throws Exception {
        customerDao.saveCustomer(newCustomer);
    }
}
