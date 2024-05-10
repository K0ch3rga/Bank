package bank.Adapters.out.PostgresJDBC;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
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
    public synchronized BankAccount createAccount(NewAccountDto account) {
        var acc = accountRepository.save(new AccountEntity(null, Long.valueOf(0),
                account.accountHolder(), account.bankId(), account.currency()));
        return new BankAccount(acc.id, acc.balance, acc.accountHolder, acc.bankId, acc.currency);
    }

    @Override
    public synchronized List<BankAccount> getAllAccountsByCustomerId(long id) {
        var accounts = accountRepository.findAllByAccountHolder(id).stream()
                .map((acc) -> new BankAccount(acc.id, acc.balance, acc.accountHolder,
                        acc.bankId, acc.currency))
                .toList();
        return accounts;
    }

    @Override
    public synchronized void updateAccount(BankAccount account) {
        AccountEntity newAccountEntity = new AccountEntity(account.id(), account.balance(), account.accountHolder(),
                account.bankId(), account.currency());
        accountRepository.save(newAccountEntity);
    }

    @Override
    public synchronized Optional<BankAccount> getAccoinyById(long id) {
        var acc = accountRepository.findById(id);
        if (acc.isPresent()) {
            return Optional.of(new BankAccount(acc.get().id, acc.get().balance, acc.get().accountHolder,
                    acc.get().bankId, acc.get().currency));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public synchronized List<BankAccount> getAll() {
        var list = new ArrayList<BankAccount>();
        accountRepository.findAll()
                .forEach(a -> list.add(new BankAccount(a.id, a.balance, a.accountHolder, a.bankId, a.currency)));
        return list;
    }

}