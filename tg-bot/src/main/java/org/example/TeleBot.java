package org.example;

import java.util.Arrays;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.example.CommandHandler;


public class TeleBot extends TelegramLongPollingBot {
    private CommandHandler m_CommandHandler;

    public TeleBot() {
        super();
        m_CommandHandler = new CommandHandler();
    }

    @Override
    public void onUpdateReceived(Update update) {
        final Message message = update.getMessage();
        String[] commandParts = message.getText().split(" ");

        String commandName = commandParts[0];
        String[] arguments = Arrays.copyOfRange(commandParts, 1, commandParts.length);

        String commandResult = m_CommandHandler.Handle(commandName, arguments);
        SendMessage(message.getChatId(), commandResult);
    }

    public void SendMessage(Long chatId, String text) {
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
        return "НАЗВАНИЕ БОТА";
    }

    @Override
    public String getBotToken() {
        return "ТОКЕН";
    }
}
