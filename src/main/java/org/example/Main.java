package org.example;

import org.example.ApiOfertas.ApiDTO;
import org.example.ApiOfertas.ApiOfertas;
import org.example.Telegram.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new Bot());

        System.out.println("Bot rodando!");
    }
}