package org.example.Commands;

import bank.Domain.Roles;

public class OpenNewAccountCommand extends Command{
    public OpenNewAccountCommand() {
        super(
                "/open_new_account",
                "Открыть новый счет",
                "/open_new_account",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role) {
        return "Открыт счет";
    }
}
