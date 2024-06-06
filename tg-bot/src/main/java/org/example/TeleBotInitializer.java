package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TeleBotInitializer {

    private final TeleBot teleBot;

    @Autowired
    public TeleBotInitializer(TeleBot teleBot) {
        this.teleBot = teleBot;
    }

    public void init() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(teleBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
