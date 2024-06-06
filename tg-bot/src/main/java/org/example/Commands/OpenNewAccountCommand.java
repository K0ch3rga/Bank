package org.example.Commands;

import bank.Adapters.out.PostgresJDBC.repositories.TgCustomerRepository;
import bank.Application.dao.AccountDao;
import bank.Application.dto.NewAccountDto;
import bank.Domain.CurrencyType;
import bank.Domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenNewAccountCommand extends Command {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TgCustomerRepository tgCustomerRepository;

    public OpenNewAccountCommand() {
        super(
                "/open_new_account",
                "Открыть новый счет",
                "/open_new_account <валюта>. Доступная валюта: RUB, USD, EUR",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        var customerEntity = tgCustomerRepository.findById(chatId);
        if (customerEntity.isEmpty())
            return "Пользователь не найден";
        var currency = CurrencyType.valueOf(args[0]);
        if (currency == null)
            return "Валюта счета указана не верно";
        accountDao.createAccount(new NewAccountDto(customerEntity.get().getCustomerId(), 0, currency));
        return "Счет успешно создан";
    }
}
