package org.example;

import java.util.Arrays;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TeleBot extends TelegramLongPollingBot {
    private CommandHandler commandHandler;
    private CommandRegister commandRegister;

    public TeleBot() {
        super();
        commandHandler = new CommandHandler();
        commandRegister = new CommandRegister(commandHandler);
        commandRegister.registerAllCommand();
    }

    @Override
    public void onUpdateReceived(Update update) {
        final Message message = update.getMessage();
        String[] commandParts = message.getText().split(" ");

        String commandName = commandParts[0];
        String[] arguments = Arrays.copyOfRange(commandParts, 1, commandParts.length);

        String commandResult = commandHandler.handle(commandName, arguments);
        sendMessage(message.getChatId(), commandResult);
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "VaultVoyagerBot";
    }

    @Override
    public String getBotToken() {
        return "6624519391:AAFidUN4j_jDp5QT6fIabVdM0HjngAYt49U";
    }
}
