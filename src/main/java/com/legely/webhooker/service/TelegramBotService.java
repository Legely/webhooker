package com.legely.webhooker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class TelegramBotService implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final String botToken;
    private final Long chatId;

    public TelegramBotService(@Value("${bot.token}") String botToken,
                              @Value("${mytgId}") Long chatId) {
        this.botToken = botToken;
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.chatId = chatId;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    public void sendMess(String sender, String messageWebhook) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text("Отправитель: " + sender + " текст сообщения: " + messageWebhook)
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("ошибка: " + e.getMessage());
        }
    }

    @Override
    public void consume(Update update) {

    }
}