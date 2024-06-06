package org.example.Commands;

import bank.Adapters.out.PostgresJDBC.repositories.TgCustomerRepository;
import bank.Application.dao.AccountDao;
import bank.Domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowMyAccountsCommand extends Command {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TgCustomerRepository tgCustomerRepository;

    public ShowMyAccountsCommand() {
        super(
                "/show_my_accounts",
                "Мои счета",
                "/show_my_accounts",
                new Roles[]{Roles.CUSTOMER, Roles.ADMIN}
        );
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        var customer = tgCustomerRepository.findById(chatId);
        if (customer.isEmpty())
            return "Пользователя не существует";
        var myAccounts = accountDao.getAllAccountsByCustomerId(customer.get().getCustomerId());
        var stringBuilder = new StringBuilder("Мои счета \n");
        if (myAccounts.stream().count() == 0)
            stringBuilder.append("\n").append("Нет доступных счетов");
        for (var account : myAccounts) {
            stringBuilder
                    .append("id: ").append(account.id()).append("\n")
                    .append("Баланс: ").append(account.balance()).append("\n")
                    .append("Валюта: ").append(account.currency()).append("\n");
        }
        return stringBuilder.toString();
    }
}
