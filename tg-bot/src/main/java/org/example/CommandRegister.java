package org.example;

import org.example.Commands.Command;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CommandRegister {

    public CommandRegister(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    private final CommandHandler commandHandler;
    @Autowired
    private GenericApplicationContext applicationContext;

    public void registerAllCommand() {
        var commands = findCommands();
        for (Command command : commands) {
            commandHandler.registerCommand(command);
        }
    }

    protected Iterable<Command> findCommands() {
        var ref = new Reflections("org.example.Commands", new SubTypesScanner(false));
        return ref.getSubTypesOf(Command.class)
                .stream()
                .filter(c -> Command.class.isAssignableFrom(c))
                .map(c -> applicationContext.getBean(c))
                .collect(Collectors.toSet());
    }
}
