package org.example;

import org.example.Commands.Command;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.util.stream.Collectors;

public class CommandRegister {
    public CommandRegister(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    private final CommandHandler commandHandler;

    public void registerAllCommand()
    {
        var commands = findCommands();
        for (Command command : commands)
        {
            commandHandler.registerCommand(command);
        }
    }

    private Iterable<Command> findCommands()
    {
        var ref = new Reflections("org.example.Commands", new SubTypesScanner(false));
        return ref.getSubTypesOf(Command.class)
                .stream()
                .filter(c -> Command.class.isAssignableFrom(c))
                .map(c -> {
                    try {
                        return c.newInstance();
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toSet());
    }
}
