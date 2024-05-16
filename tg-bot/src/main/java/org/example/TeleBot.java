package org.example;

import java.util.Arrays;

import bank.Adapters.out.PostgresJDBC.repositories.CustomerRepository;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TeleBot extends TelegramLongPollingBot {
    @Autowired
    private CommandHandler commandHandler;
    @Autowired
    private CommandRegister commandRegister;


    public TeleBot() {
        super();
        commandRegister.registerAllCommand();
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
        return "6624519391:AAGh_Rzyty0ZocCpF8-nRYAUgaNT30ntG8k";
    }
}
