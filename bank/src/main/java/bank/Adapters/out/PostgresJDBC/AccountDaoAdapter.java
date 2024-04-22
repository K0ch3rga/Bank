package bank.Adapters.out.PostgresJDBC;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.Adapters.out.PostgresJDBC.entities.AccountEntity;
import bank.Adapters.out.PostgresJDBC.repositories.AccountRepository;
import bank.Application.dao.AccountDao;
import bank.Application.dto.NewAccountDto;
import bank.Domain.BankAccount;

@Service
public class AccountDaoAdapter implements AccountDao {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public BankAccount createAccount(NewAccountDto account) {
        var acc = accountRepository.save(new AccountEntity(Math.abs(new Random().nextLong()), 0,
                account.accountHolder(), account.bankId(), account.currency()));
        return new BankAccount(acc.id, acc.balance, acc.accountHolder, acc.bankId, acc.currency);
    }

    @Override
    public List<BankAccount> getAllAccountsByCustomerId(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllAccountsByCustimerId'");
    }

    @Override
    public void updateAccount(BankAccount account) {
        AccountEntity newAccountEntity = new AccountEntity(account.id(), account.balance(), account.accountHolder(),
                account.bankId(), account.currency());
        accountRepository.save(newAccountEntity);
    }

    @Override
    public Optional<BankAccount> getAccoinyById(long id) {
        var acc = accountRepository.findById(id);
        if (acc.isPresent()) {
            return Optional.of(new BankAccount(acc.get().id, acc.get().balance, acc.get().accountHolder,
                    acc.get().bankId, acc.get().currency));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<BankAccount> getAll() {
        var list = new ArrayList<BankAccount>();
        accountRepository.findAll()
                .forEach(a -> list.add(new BankAccount(a.id, a.balance, a.accountHolder, a.bankId, a.currency)));
        return list;
    }

}