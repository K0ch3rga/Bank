package org.example;

import bank.Domain.Customer;
import bank.Domain.Roles;
import jakarta.inject.Inject;
import org.example.Commands.Command;

import java.util.*;

import bank.Adapters.out.PostgresJDBC.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

@Service
public class CommandHandler implements ICommandHandler {

    @Autowired
    private CustomerRepository customerRepository;
    private static final ArrayList<Command> commands = new ArrayList<>();

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


    public String handle(String command, String[] args, Long chatId) {
        var cmd = findCommandByName(command);
        var user = customerRepository.findFirtById(chatId);

        Set<Roles> userRoles = new HashSet<>();
        if (cmd == null)
            return String.format("Неизвестная команда %s", command);

        if (user == null)
            userRoles.add(Roles.NOTAUTHORIZED);
        else {
            var userEntity = user.toRecord();
            userRoles = userEntity.roles();
        }
        var cmdRoles = cmd.RequiredRoles;
        if (userRoles.stream().anyMatch(r-> Arrays.stream(cmdRoles).toList().contains(r))) {
            int maxPriority = userRoles.stream().map(Roles::getPriority).max(Comparator.naturalOrder()).get();
            Roles role = Arrays.stream(Roles.values()).filter(r->r.getPriority() == maxPriority).findFirst().get();

            return cmd.execute(args,role);
        }

        return String.format("У вас недостаточно прав для выполнения команды %s", command);
    }

    public static Command[] getAvailableCommandsName(Roles role) {
        return commands.stream()
                .filter(c -> Arrays.stream(c.RequiredRoles).anyMatch(r -> r.equals(role)))
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
