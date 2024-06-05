package org.example.Commands;


import bank.Application.usecases.TransferUsecase;
import bank.Domain.Roles;
import bank.Infrastructure.AccountDoesntExistExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpBalanceCommand extends Command {
    @Autowired
    private TransferUsecase transferUsecase;

    public UpBalanceCommand() {
        super(
                "/up_balance",
                "Пополнение баланса",
                "/up_balance <номер_счета> <сумма>",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        var accountId = Long.parseLong(args[0]);
        var count = Long.parseLong(args[1]);
        try {
            transferUsecase.refillMoney(accountId, count);
        } catch (AccountDoesntExistExeption e) {
            return "Счета не существует";
        }

        return "Счет " + accountId + " Пополнен на: " + count;
    }
}
