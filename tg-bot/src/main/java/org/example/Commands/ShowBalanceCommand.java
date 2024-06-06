package org.example.Commands;

import bank.Adapters.out.PostgresJDBC.repositories.CustomerRepository;
import bank.Application.dao.AccountDao;
import bank.Application.dao.TransactionDao;
import bank.Domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowBalanceCommand extends Command {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionDao transactionDao;

    private AccountDao accountDao;

    public ShowBalanceCommand() {
        super("/show_balance",
                "Мой баланс",
                "/show_balance <номер_счета>",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role, long ChatId) {
        var accountId = Long.parseLong(args[0]);
        var account = accountDao.getAccoinyById(accountId);
        var transactions = transactionDao.getAllbyAccountId(accountId);
        if (account == null)
            return "Счета с таким id не существует";
        StringBuilder resultString = new StringBuilder("Ваш баланс: " + account.get().balance());
        resultString.append("\n История транзакций:");
        for (var transaction : transactions) {
            resultString
                    .append("\n")
                    .append("Время:").append(transaction.time())
                    .append("Сумма: ").append(transaction.amount())
                    .append("Счет: ").append(transaction.bankAccountId());
        }
        return resultString.toString();
    }
}
