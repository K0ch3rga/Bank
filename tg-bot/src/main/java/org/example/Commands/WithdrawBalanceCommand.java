package org.example.Commands;

import bank.Domain.Roles;

public class WithdrawBalanceCommand extends Command{
    public WithdrawBalanceCommand() {
        super(
                "/withdraw_balance",
                "Снять деньги со счета",
                "/withdraw_balance <номер_счета> <сумма>",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role) {
        return "Вывод средств";
    }
}
