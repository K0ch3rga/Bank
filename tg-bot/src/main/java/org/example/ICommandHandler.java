package org.example;

import bank.Domain.Roles;
import org.example.Commands.Command;

import java.util.ArrayList;

public interface ICommandHandler {
    ArrayList<Command> commands = new ArrayList<>();
    void registerCommand(Command command);
    Command findCommandByName(String commandName);
    String handle(String commandName, String[] args, Long chatId);

}
