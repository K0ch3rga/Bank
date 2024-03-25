package bank.Adapters.out;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import bank.Application.dao.AccountDao;
import bank.Application.dto.NewAccountDto;
import bank.Domain.BankAccount;
import bank.Domain.CurrencyType;

@Component
public class FakeRepository implements AccountDao{

    @Override
    public void createAccount(NewAccountDto account) {

    }

    @Override
    public List<BankAccount> getAllAccountsByCustimerId(long id) {
        var list = new ArrayList<BankAccount>();
        list.add(new BankAccount(id, 0, 500, 500, CurrencyType.RUB));
        return list;
    }

    @Override
    public void updateAccount(BankAccount account) {

    }

    @Override
    public Optional<BankAccount> getAccoinyById(long id) {
        return Optional.of(new BankAccount(id, 0, 500, 500, CurrencyType.RUB));
    }
    
}
