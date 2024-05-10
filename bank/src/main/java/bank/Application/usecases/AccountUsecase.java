package bank.Application.usecases;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.Application.dao.AccountDao;
import bank.Application.dto.NewAccountDto;
import bank.Domain.BankAccount;
import bank.Infrastructure.AccountDoesntExistExeption;

/**
 * Класс для взаимодействия с аккаунтом
 * (Создание, получение и обновление)
 * @see TransferUsecase
 */
@Service
public class AccountUsecase {
    
    private final AccountDao accountDao;
    @Autowired
    public AccountUsecase(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * Создаёт новый акаунт
     * @param account
     */
    public BankAccount createNewAccount(NewAccountDto account) {
        return accountDao.createAccount(account);
    }

    /**
     * Возвращает аккаунт полученный по id
     * @param id
     * @return
     * @throws AccountDoesntExistExeption
     */
    public Optional<BankAccount> getAccountById(long id) {
        return accountDao.getAccoinyById(id);
    }

    /**
     * Обновляет аккаунт по id с предоставленными данными
     * Не должне менять баланс
     * @param account
     */
    public void updateAccount(BankAccount account) {
        //TODO add checks on balance
        accountDao.updateAccount(account);
    }

    public List<BankAccount> getAll() {
        return accountDao.getAll();
    }
}
