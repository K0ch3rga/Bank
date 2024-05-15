package org.example.Commands;

import org.example.Roles;

public class OpenNewAccountCommand extends Command{
    public OpenNewAccountCommand() {
        super(
                "/open_new_account",
                "Открыть новый счет",
                "/open_new_account",
                Roles.CUSTOMER);
    }

    @Override
    public String execute(String[] args) {
        return null;
    }
}
