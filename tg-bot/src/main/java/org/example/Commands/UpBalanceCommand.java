package org.example.Commands;


import org.example.Roles;

public class UpBalanceCommand extends Command{
    public UpBalanceCommand() {
        super(
                "/up_balance",
                "Пополнение баланса",
                "/up_balance <номер_счета> <сумма>",
                Roles.CUSTOMER);
    }

    @Override
    public String execute(String[] args) {
        return null;
    }
}
