package org.example;

// import org.springframework.boot.SpringApplication;
import bank.BankApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication(scanBasePackages = {"bank", "org"})
@EnableJpaRepositories
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        init();

    }

    public static void init() {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TeleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}