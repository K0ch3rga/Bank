package bank.Application.usecases;

import java.util.Optional;

import bank.Application.dao.AccountDao;
import bank.Application.dto.NewAccountDto;
import bank.Domain.BankAccount;
import bank.Infrastructure.AccountDoesntExistExeption;

public class AccountUsecase {
    private AccountDao accountDao;

    public void createNewAccount(NewAccountDto account) {
        accountDao.createAccount(account);
    }

    public Optional<BankAccount> getAccountById(int id) throws AccountDoesntExistExeption {
        // TODO choose optional or exeption
        return accountDao.getAccoinyById(id);
    }

    public void updateAccount(BankAccount account) {
        //TODO add checks on balance
        accountDao.updateAccount(account);
    }
}
