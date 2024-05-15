package org.example.Commands;

import org.example.CommandHandler;
import org.example.Roles;

import java.util.function.Function;

public class HelpCommand extends Command {


    public HelpCommand(CommandHandler commandHandler) {
        super("/help", "Вывести список команд", "/help", Roles.CUSTOMER);
    }

    @Override
    public String execute(String[] args) {
        String helpText = "Список команд:\n\n";
/*        var commandList = commandHandler.getAvailableCommandName(Roles.CUSTOMER);
        for (int i = 0; i < commandList.length; ++i)
            helpText += commandList[i].Description + ":\n" + commandList[i].Example + "\n\n";*/
        return helpText;
    }
}
