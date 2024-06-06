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
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountDaoAdapter implements AccountDao {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public synchronized BankAccount createAccount(NewAccountDto account) {
        var acc = accountRepository.save(new AccountEntity(null, 0l,
                account.accountHolder(), account.bankId(), account.currency()));
        return new BankAccount(acc.getId(), acc.getBalance(), acc.getAccountHolder(), acc.getBankId(),
                acc.getCurrencyType());
    }

    @Override
    @Transactional
    public synchronized List<BankAccount> getAllAccountsByCustomerId(long id) {
        var accounts = accountRepository.findAllByAccountHolder(id).stream()
                .map((acc) -> new BankAccount(acc.getId(), acc.getBalance(), acc.getAccountHolder(),
                        acc.getBankId(), acc.getCurrencyType()))
                .toList();
        return accounts;
    }

    @Override
    @Transactional
    public synchronized void updateAccount(BankAccount account) {
        Optional<AccountEntity> oldAccountEntity = accountRepository.findById(account.id());
        if (oldAccountEntity.isPresent()) {
            AccountEntity newAccountEntity = oldAccountEntity.get();
            newAccountEntity.setBalance(account.balance());
            newAccountEntity.setAccountHolder(account.accountHolder());
            newAccountEntity.setBankId(account.bankId());
            newAccountEntity.setCurrencyType(account.currency());
            accountRepository.save(newAccountEntity);
        } else {
            AccountEntity newAccountEntity = new AccountEntity(account.id(), account.balance(), account.accountHolder(),
                    account.bankId(), account.currency());
            accountRepository.save(newAccountEntity);
        }
    }

    @Override
    @Transactional
    public synchronized Optional<BankAccount> getAccoinyById(long id) {
        var acc = accountRepository.findById(id);
        if (acc.isPresent()) {
            return Optional.of(new BankAccount(acc.get().getId(), acc.get().getBalance(), acc.get().getAccountHolder(),
                    acc.get().getBankId(), acc.get().getCurrencyType()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public synchronized List<BankAccount> getAll() {
        var list = new ArrayList<BankAccount>();
        accountRepository.findAll()
                .forEach(a -> list.add(new BankAccount(a.getId(), a.getBalance(), a.getAccountHolder(), a.getBankId(),
                        a.getCurrencyType())));
        return list;
    }

}