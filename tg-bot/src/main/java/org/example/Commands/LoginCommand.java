package org.example.Commands;

import bank.Adapters.out.PostgresJDBC.CustomerDaoAdapter;
import bank.Adapters.out.PostgresJDBC.entities.TgCustomerEntity;
import bank.Adapters.out.PostgresJDBC.repositories.TgCustomerRepository;
import bank.Domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginCommand extends Command {
    @Autowired
    private CustomerDaoAdapter customerDaoAdapter;
    @Autowired
    private TgCustomerRepository tgCustomerRepository;

    public LoginCommand() {
        super(
                "/login",
                "Вход в аккаунт",
                "/login <email> <пароль>",
                new Roles[]{Roles.NOTAUTHORIZED});
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        var email = args[0];
        var password = args[1];
        var customer = customerDaoAdapter.loadCustomerByEmail(email);
        if (customer.isEmpty())
            return "Аккаунта не существует";
        if (customer.get().toRecord().password().equals(password)) {
            var tgCustomer = new TgCustomerEntity(customer.get().getId(), chatId);
            tgCustomerRepository.save(tgCustomer);
            return "Успешная авторизация";
        }
        return "Неверный пароль или почта";
    }
}
