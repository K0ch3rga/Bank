package bank.Application.dao;

import java.util.List;
import java.util.Optional;

import bank.Application.dto.NewAccountDto;
import bank.Domain.BankAccount;

public interface AccountDao {
    void createAccount(NewAccountDto account);
    List<BankAccount> getAllAccountsByCustimerId(int id);
    void updateAccount(BankAccount account);
    Optional<BankAccount> getAccoinyById(int id);
    // no deletion
}
