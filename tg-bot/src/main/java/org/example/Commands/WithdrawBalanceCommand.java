package org.example.Commands;

import bank.Application.usecases.TransferUsecase;
import bank.Domain.Roles;
import bank.Infrastructure.AccountDoesntExistExeption;
import bank.Infrastructure.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithdrawBalanceCommand extends Command {
    @Autowired
    private TransferUsecase transferUsecase;

    public WithdrawBalanceCommand() {
        super(
                "/withdraw_balance",
                "Снять деньги со счета",
                "/withdraw_balance <номер_счета> <сумма>",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        var accountId = Long.parseLong(args[0]);
        var count = Long.parseLong(args[1]);
        try {
            transferUsecase.withdrawMoney(accountId, count);
        } catch (AccountDoesntExistExeption a) {
            return "Счета не существует";
        } catch (InsufficientFundsException c) {
            return "Недостаточно средств";
        }
        return "Средста выведены";
    }
}
