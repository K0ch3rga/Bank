package org.example.Commands;

import bank.Application.usecases.TransferUsecase;
import bank.Domain.Roles;
import bank.Infrastructure.AccountDoesntExistExeption;
import bank.Infrastructure.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferMoneyCommand extends Command {
    @Autowired
    private TransferUsecase transferUsecase;

    public TransferMoneyCommand() {
        super(
                "/transfer_money",
                "Перевод средств",
                "/transfer_money <номер_своего_счета> <номер_счета_получателя> <сумма>",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        var currentAccountId = Long.parseLong(args[0]);
        var targetAccountId = Long.parseLong(args[1]);
        var count = Long.parseLong(args[2]);
        if (currentAccountId == targetAccountId)
            return "Ошибка: Номера счетов совпадают.";
        try {
            transferUsecase.transferMoney(currentAccountId, targetAccountId, count);
        } catch (AccountDoesntExistExeption a) {
            return "Счета не существует";
        } catch (InsufficientFundsException m) {
            return "Не хватает средств";
        }
        return "Перевод выполнен";
    }
}
