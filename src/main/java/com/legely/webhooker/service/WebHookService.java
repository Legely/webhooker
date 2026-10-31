package com.legely.webhooker.service;


import com.legely.webhooker.dto.WebHookRequest;
import com.legely.webhooker.dto.WebhookResponce;
import com.legely.webhooker.dto.WebhookStatsResponce;
import com.legely.webhooker.entity.WebHookEvent;
import com.legely.webhooker.repository.WebHookRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebHookService {

    private final WebHookRepository repository;
    private final TelegramBotService telegramBotService;

    public WebHookService(WebHookRepository repository, TelegramBotService telegramBotService) {
        this.repository = repository;
        this.telegramBotService = telegramBotService;
    }


    // обработка принятия webhookа
    @Transactional
    public WebhookResponce createEvent(WebHookRequest request) {
        WebHookEvent webHookEvent = new WebHookEvent(request.sender(), request.message());
        WebHookEvent savedEvent = repository.save(webHookEvent);

        telegramBotService.MessHandler(request.sender(), request.message());
        return new WebhookResponce(
                savedEvent.getSender(),
                savedEvent.getMessage(),
                savedEvent.getTimeSend()
        );
    }



   // выдача статистики
    @Transactional(readOnly = true)
    public List<WebHookEvent> findAll(){
        return repository.findAll();
    }


}
