package org.example.Commands;

import bank.Domain.Roles;

public class TransferMoneyCommand extends Command{
    public TransferMoneyCommand() {
        super(
                "/transfer_money",
                "Перевод средств",
                "/transfer_money <номер_своего_счета> <номер_счета_получателя> <сумма>",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role) {
        return "Перевод";
    }
}
