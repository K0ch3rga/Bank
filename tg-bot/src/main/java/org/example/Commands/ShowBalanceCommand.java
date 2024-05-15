package org.example.Commands;

import org.example.Roles;

public class ShowBalanceCommand extends Command {
    public ShowBalanceCommand() {
        super("/show_balance",
                "Мой баланс",
                "/show_balance",
                Roles.CUSTOMER);
    }

    @Override
    public String execute(String[] args) {
        return null;
    }
}
