package org.example.Commands;

import bank.Adapters.out.PostgresJDBC.repositories.TgCustomerRepository;
import bank.Domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutCommand extends Command {
    @Autowired
    private TgCustomerRepository tgCustomerRepository;

    public LogoutCommand() {
        super(
                "/logout",
                "Выход из аккаунта",
                "/logout",
                new Roles[]{Roles.CUSTOMER});
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        tgCustomerRepository.deleteById(chatId);
        return "Выход из системы";
    }
}
