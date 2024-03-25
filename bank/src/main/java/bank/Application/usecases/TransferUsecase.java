package bank.Application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.Application.dao.AccountDao;
import bank.Domain.BankAccount;
import bank.Infrastructure.AccountDoesntExistExeption;
import bank.Infrastructure.InsufficientFunds;

/**
 * Класс для взаимодействия с балансом счёта
 * (Снятие, пополнение и перевода между счетами)
 * @see AccountUsecase
 */
@Service
public class TransferUsecase {
    private final AccountDao accountDao;
    @Autowired
    public TransferUsecase(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * Метод для перевода денег между счетами
     * Автоматически приводит деньги к другой валюте
     * @param fromId
     * @param toId
     * @param count - целочисленное значение, где берётся самая меньшая единица валюты
     * (Для рубля - копейки)
     * @throws AccountDoesntExistExeption
     * @throws InsufficientFunds
     */
    public void transferMoney(long fromId, long toId, long count) throws AccountDoesntExistExeption, InsufficientFunds {
        var from = accountDao.getAccoinyById(fromId);
        var to = accountDao.getAccoinyById(toId);
        if (!from.isPresent()) 
            throw new AccountDoesntExistExeption("", fromId);
        if (!to.isPresent())
            throw new AccountDoesntExistExeption("", toId);

        var fromAcc = from.get();
        var toAcc = to.get();

        if (fromAcc.balance() < count)
            throw new InsufficientFunds("Not enough money at ", fromAcc.id());

        if (fromAcc.currency() == toAcc.currency()) {
            var newFrom = new BankAccount(fromAcc.id(), fromAcc.balance() - count, fromAcc.accountHolder(), fromAcc.bankId(), fromAcc.currency());
            var newTo = new BankAccount(toAcc.id(), toAcc.balance() + count, toAcc.accountHolder(), toAcc.bankId(), toAcc.currency());
            accountDao.updateAccount(newFrom);
            accountDao.updateAccount(newTo);
        } else {
            long convertedCount = (long) count * 5 / 100; //FIXME
            var newFrom = new BankAccount(fromAcc.id(), fromAcc.balance() - count, fromAcc.accountHolder(), fromAcc.bankId(), fromAcc.currency());
            var newTo = new BankAccount(toAcc.id(), toAcc.balance() + convertedCount, toAcc.accountHolder(), toAcc.bankId(), toAcc.currency());
            accountDao.updateAccount(newFrom);
            accountDao.updateAccount(newTo);
        }
    }

    /**
     * Метод для пополнения счёта
     * @param fromId
     * @param count - целочисленное значение, где берётся самая меньшая единица валюты
     * (Для рубля - копейки)
     */
    public void withdrawMoney(long fromId, long count) {
        var from = accountDao.getAccoinyById(fromId);
        if (!from.isPresent()) 
            throw new AccountDoesntExistExeption("", fromId);
        var fromAcc = from.get();

        if (fromAcc.balance() < count)
            throw new InsufficientFunds("Not enough money at ", fromAcc.id());

        var newFrom = new BankAccount(fromAcc.id(), fromAcc.balance() - count, fromAcc.accountHolder(), fromAcc.bankId(), fromAcc.currency());
        accountDao.updateAccount(newFrom);
    }

    /**
     * Метод для снятия денег со счёта
     * @param toId
     * @param count - целочисленное значение, где берётся самая меньшая единица валюты
     * (Для рубля - копейки)
     */
    public void refillMoney(long toId, long count) {
        //TODO check if money is right currency
        var to = accountDao.getAccoinyById(toId);
        if (!to.isPresent()) 
            throw new AccountDoesntExistExeption("", toId);
        var toAcc = to.get();
        var newTo = new BankAccount(toAcc.id(), toAcc.balance() + count, toAcc.accountHolder(), toAcc.bankId(), toAcc.currency());
        accountDao.updateAccount(newTo);
    }
}
