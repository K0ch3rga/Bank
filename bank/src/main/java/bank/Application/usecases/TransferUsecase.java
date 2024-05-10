package bank.Application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import bank.Application.dao.AccountDao;
import bank.Application.dao.TransactionDao;
import bank.Application.dto.NewTransactionDto;
import bank.Domain.BankAccount;
import bank.Domain.TransactionType;
import bank.Infrastructure.AccountDoesntExistExeption;
import bank.Infrastructure.InsufficientFundsException;

/**
 * Класс для взаимодействия с балансом счёта
 * (Снятие, пополнение и перевода между счетами)
 * 
 * @see AccountUsecase
 */
@Service
public class TransferUsecase {
    private final AccountDao accountDao;
    private final TransactionDao transactionDao;
    private final CursService cursService; 

    @Autowired
    public TransferUsecase(AccountDao accountDao, TransactionDao transactionDao, CursService cursService) {
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
        this.cursService = cursService;
    }

    /**
     * Метод для перевода денег между счетами
     * Автоматически приводит деньги к другой валюте
     * 
     * @param fromId
     * @param toId
     * @param count  - целочисленное значение, где берётся самая меньшая единица
     *               валюты
     *               (Для рубля - копейки)
     * @throws AccountDoesntExistExeption
     * @throws InsufficientFunds
     */
    public void transferMoney(long fromId, long toId, long count)
            throws AccountDoesntExistExeption, InsufficientFundsException {
        var from = accountDao.getAccoinyById(fromId);
        var to = accountDao.getAccoinyById(toId);
        if (!from.isPresent())
            throw new AccountDoesntExistExeption("", fromId);
        if (!to.isPresent())
            throw new AccountDoesntExistExeption("", toId);

        var fromAcc = from.get();
        var toAcc = to.get();

        if (fromAcc.balance() < count)
            throw new InsufficientFundsException("Not enough money at ", fromAcc.id());

        long convertedCount = count;
        if (fromAcc.currency() != toAcc.currency()) {
            float fromRate = cursService.getCost(fromAcc.currency());
            float toRate = cursService.getCost(toAcc.currency());
            long fromConvertedCount = (long) (count * fromRate);
            long toConvertedCount = (long) (fromConvertedCount / toRate);
            convertedCount = toConvertedCount;
        }

        var newFrom = new BankAccount(fromAcc.id(), fromAcc.balance() - count, fromAcc.accountHolder(),
                fromAcc.bankId(), fromAcc.currency());
        var newTo = new BankAccount(toAcc.id(), toAcc.balance() + convertedCount, toAcc.accountHolder(), toAcc.bankId(),
                toAcc.currency());
        accountDao.updateAccount(newFrom);
        accountDao.updateAccount(newTo);
        addTransaction(new NewTransactionDto(convertedCount, newTo.currency(), LocalDateTime.now(),
                TransactionType.TRANSFER, newFrom.id(), newTo.id()));
    }

    /**
     * Метод для пополнения счёта
     * 
     * @param fromId
     * @param count  - целочисленное значение, где берётся самая меньшая единица
     *               валюты
     *               (Для рубля - копейки)
     */
    public void withdrawMoney(long fromId, long count) throws AccountDoesntExistExeption, InsufficientFundsException {
        var from = accountDao.getAccoinyById(fromId);
        if (!from.isPresent())
            throw new AccountDoesntExistExeption("", fromId);
        var fromAcc = from.get();

        if (fromAcc.balance() < count)
            throw new InsufficientFundsException("Not enough money at ", fromAcc.id());

        var newFrom = new BankAccount(fromAcc.id(), fromAcc.balance() - count, fromAcc.accountHolder(),
                fromAcc.bankId(), fromAcc.currency());
        accountDao.updateAccount(newFrom);
        addTransaction(new NewTransactionDto(count, newFrom.currency(), LocalDateTime.now(),
                TransactionType.WITHDRAWAL, newFrom.id(), newFrom.id()));
    }

    /**
     * Метод для снятия денег со счёта
     * 
     * @param toId
     * @param count - целочисленное значение, где берётся самая меньшая единица
     *              валюты
     *              (Для рубля - копейки)
     */
    public void refillMoney(long toId, long count) {
        // TODO check if money is right currency
        var to = accountDao.getAccoinyById(toId);
        if (!to.isPresent())
            throw new AccountDoesntExistExeption("", toId);
        var toAcc = to.get();
        var newTo = new BankAccount(toAcc.id(), toAcc.balance() + count, toAcc.accountHolder(), toAcc.bankId(),
                toAcc.currency());
        accountDao.updateAccount(newTo);
        addTransaction(new NewTransactionDto(count, newTo.currency(), LocalDateTime.now(), TransactionType.REFILL,
                newTo.id(), newTo.id()));
    }

    private void addTransaction(NewTransactionDto transaction) {
        transactionDao.createTransaction(transaction);
    }
}
