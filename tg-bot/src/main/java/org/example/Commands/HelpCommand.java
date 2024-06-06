package org.example.Commands;

import bank.Domain.Roles;
import org.example.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand extends Command {
    public HelpCommand() {
        super("/help",
                "Вывести список команд",
                "/help",
                new Roles[]{Roles.CUSTOMER, Roles.NOTAUTHORIZED});
    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        String helpText = "Список команд:\n\n";
        var commandList = CommandHandler.getAvailableCommandsName(role);
        for (int i = 0; i < commandList.length; ++i)
            helpText += commandList[i].Description + ":\n" + commandList[i].Example + "\n\n";
        return helpText;
    }
}
