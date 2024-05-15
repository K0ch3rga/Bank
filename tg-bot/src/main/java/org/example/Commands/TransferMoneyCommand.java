package org.example.Commands;

import org.example.Roles;

public class TransferMoneyCommand extends Command{
    public TransferMoneyCommand() {
        super(
                "/transfer_money",
                "Перевод средств",
                "/transfer_money <номер_своего_счета> <номер_счета_получателя> <сумма>",
                Roles.CUSTOMER);
    }

    @Override
    public String execute(String[] args) {
        return null;
    }
}
