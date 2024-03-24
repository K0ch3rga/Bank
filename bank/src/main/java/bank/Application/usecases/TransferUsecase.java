package bank.Application.usecases;

import org.springframework.stereotype.Service;

import bank.Application.dao.AccountDao;
import bank.Domain.BankAccount;
import bank.Infrastructure.AccountDoesntExistExeption;
import bank.Infrastructure.InsufficientFunds;

@Service
public class TransferUsecase {
    private AccountDao accountDao;

    public void transferMoney(int fromId, int toId, int count) throws AccountDoesntExistExeption, InsufficientFunds {
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
            int convertedCount = (int) count * 5 / 100; //FIXME
            var newFrom = new BankAccount(fromAcc.id(), fromAcc.balance() - count, fromAcc.accountHolder(), fromAcc.bankId(), fromAcc.currency());
            var newTo = new BankAccount(toAcc.id(), toAcc.balance() + convertedCount, toAcc.accountHolder(), toAcc.bankId(), toAcc.currency());
            accountDao.updateAccount(newFrom);
            accountDao.updateAccount(newTo);
        }
    }

    public void withdrawMoney(int fromId, int count) {
        var from = accountDao.getAccoinyById(fromId);
        if (!from.isPresent()) 
            throw new AccountDoesntExistExeption("", fromId);
        var fromAcc = from.get();

        if (fromAcc.balance() < count)
            throw new InsufficientFunds("Not enough money at ", fromAcc.id());

        var newFrom = new BankAccount(fromAcc.id(), fromAcc.balance() - count, fromAcc.accountHolder(), fromAcc.bankId(), fromAcc.currency());
        accountDao.updateAccount(newFrom);
    }

    public void refillMoney(int toId, int count) {
        //TODO check if money is right currency
        var to = accountDao.getAccoinyById(toId);
        if (!to.isPresent()) 
            throw new AccountDoesntExistExeption("", toId);
        var toAcc = to.get();
        var newTo = new BankAccount(toAcc.id(), toAcc.balance() + count, toAcc.accountHolder(), toAcc.bankId(), toAcc.currency());
        accountDao.updateAccount(newTo);
    }
}
