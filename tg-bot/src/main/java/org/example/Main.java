package org.example;

// import org.springframework.boot.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"bank", "org.example"})
@EnableJpaRepositories
public class Main {

    private static TeleBotInitializer teleBot;

    @Autowired
    public Main(TeleBotInitializer teleBot) {
        this.teleBot = teleBot;
    }


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        teleBot.init();
    }
}