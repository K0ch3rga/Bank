package org.example;

import bank.Adapters.out.PostgresJDBC.repositories.CustomerRepository;
import bank.Adapters.out.PostgresJDBC.repositories.TgCustomerRepository;
import bank.Domain.Roles;
import org.example.Commands.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommandHandler implements ICommandHandler {

    @Autowired
    private TgCustomerRepository tgCustomerRepository;

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
        var user = tgCustomerRepository.findById(chatId);

        Set<Roles> userRoles = new HashSet<>();
        if (cmd == null)
            return String.format("Неизвестная команда %s", command);

        if (user.isEmpty())
            userRoles.add(Roles.NOTAUTHORIZED);
        else {
            var userEntity = customerRepository.findById(user.get().getCustomerId());
            userRoles = userEntity.get().toRecord().roles();
        }
        var cmdRoles = cmd.RequiredRoles;
        if (userRoles.stream().anyMatch(r -> Arrays.stream(cmdRoles).toList().contains(r))) {
            int maxPriority = userRoles.stream().map(Roles::getPriority).max(Comparator.naturalOrder()).get();
            Roles role = Arrays.stream(Roles.values()).filter(r -> r.getPriority() == maxPriority).findFirst().get();

            return cmd.execute(args, role, chatId);
        }

        return String.format("У вас недостаточно прав для выполнения команды %s", command);
    }

    public static Command[] getAvailableCommandsName(Roles role) {
        return commands.stream()
                .filter(c -> Arrays.stream(c.RequiredRoles).anyMatch(r -> r.equals(role)))
                .toArray(Command[]::new);
    }

}
