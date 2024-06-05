package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

@Service
public class TeleBot extends TelegramLongPollingBot {

    private CommandHandler commandHandler;
    private CommandRegister commandRegister;

    @Autowired
    public TeleBot(CommandHandler commandHandler, CommandRegister commandRegister) {
        super();
        this.commandHandler = commandHandler;
        this.commandRegister = commandRegister;
        this.commandRegister.registerAllCommand();
    }

    @Override
    public void onUpdateReceived(Update update) {
        final Message message = update.getMessage();
        String[] commandParts = message.getText().split(" ");

        String commandName = commandParts[0];
        String[] arguments = Arrays.copyOfRange(commandParts, 1, commandParts.length);
        String commandResult = commandHandler.handle(commandName, arguments, message.getChatId());
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
        return "";
    }
}
