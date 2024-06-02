package org.example;

import bank.Domain.Roles;
import org.example.Commands.Command;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ICommandHandler {
    ArrayList<Command> commands = new ArrayList<>();
    void registerCommand(Command command);
    Command findCommandByName(String commandName);
    String handle(String commandName, String[] args, Long chatId);

}
