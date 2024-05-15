package org.example.Commands;

import org.example.Roles;

public class WithdrawBalanceCommand extends Command{
    public WithdrawBalanceCommand() {
        super(
                "/withdraw_balance",
                "Снять деньги со счета",
                "/withdraw_balance <номер_счета> <сумма>",
                Roles.CUSTOMER);
    }

    @Override
    public String execute(String[] args) {
        return null;
    }
}
