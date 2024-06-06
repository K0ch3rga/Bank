package org.example.Commands;

import bank.Domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class StartCommand extends Command {
    @Autowired
    private ApplicationContext context;

    public StartCommand() {
        super(
                "/start",
                "start",
                "/start",
                new Roles[]{Roles.NOTAUTHORIZED});
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        var help = (HelpCommand) context.getBean("helpCommand");
        return "Добро пожаловать в банк \"Мы возьмем Ваши деньги\"!\n" + help.execute(args, role, chatId);
    }
}
