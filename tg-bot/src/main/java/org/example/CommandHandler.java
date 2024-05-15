package org.example;

import org.example.Commands.Command;
import java.util.ArrayList;

public class CommandHandler implements ICommandHandler {
    private final ArrayList<Command> commands = new ArrayList<>();

    public void registerCommand(Command command) {
        commands.add(command);
    }

    @Override
    public Command findCommandByName(String commandName) {
        return commands.stream()
                .filter(c -> c.Name.equals(commandName))
                .findAny()
                .orElse(null);
    }
    public CommandHandler() {}

    public String handle(String command, String[] args) {
        var cmd = findCommandByName(command);
        if (cmd == null)
            return String.format("Неизвестная команда %s", command) ;

        return cmd.execute(args);
    }

    @Override
    public Command[] getAvailableCommandName(Roles role) {
        return commands.stream()
                .filter(c -> c.RequiredRole.equals(role))
                .toArray(Command[]::new);
    }

/*    private String registrationCustomer() {
        return "rega";
    }

    private String _UpBalance(String[] arguments) {
        if (arguments.length != 2)
            return GetSyntaxError(0);
        return "_UpBalance";
    }
    private String _WithdrawBalance(String[] arguments) {
        if (arguments.length != 2)
            return GetSyntaxError(1);
        return "_WithdrawBalance";
    }
    private String _TransferMoney(String[] arguments) {
        if (arguments.length != 3)
            return GetSyntaxError(2);
        return "_TransferMoney";
    }
    private String _OpenNewAccount() {
        return "_OpenNewAccount";
    }
    private String _ShowBalance() {
        return "_ShowBalance";
    }*/
/*    private String _Help() {
        String helpText = "Список команд:\n\n";
        for (int i = 0; i < m_ComandList.length; ++i)
            helpText += m_ComandList[i].Description + ":\n" + m_ComandList[i].Example + "\n\n";
        return helpText;
    }*/

}
