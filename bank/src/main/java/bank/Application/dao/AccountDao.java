package bank.Application.dao;

import java.util.List;
import java.util.Optional;

import bank.Application.dto.NewAccountDto;
import bank.Domain.BankAccount;

public interface AccountDao {
    BankAccount createAccount(NewAccountDto account);
    List<BankAccount> getAllAccountsByCustomerId(long id);
    void updateAccount(BankAccount account);
    Optional<BankAccount> getAccoinyById(long id);
    // no deletion
    List<BankAccount> getAll();
}
