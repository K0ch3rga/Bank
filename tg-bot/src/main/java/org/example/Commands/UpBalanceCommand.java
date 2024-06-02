package org.example.Commands;


import bank.Domain.Roles;

public class UpBalanceCommand extends Command{
    public UpBalanceCommand() {
        super(
                "/up_balance",
                "Пополнение баланса",
                "/up_balance <номер_счета> <сумма>",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role) {
        return "Пополнен баланс";
    }
}
