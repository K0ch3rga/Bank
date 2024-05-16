package org.example.Commands;

import bank.Domain.Roles;

public class ShowBalanceCommand extends Command {
    public ShowBalanceCommand() {
        super("/show_balance",
                "Мой баланс",
                "/show_balance",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role) {
        return null;
    }
}
