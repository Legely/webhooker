package com.legely.webhooker.controller;


import com.legely.webhooker.dto.WebHookRequest;
import com.legely.webhooker.dto.WebhookResponce;
import com.legely.webhooker.entity.WebHookEvent;
import com.legely.webhooker.service.WebHookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WebhookController {

    WebHookService webHookService;

    public WebhookController(WebHookService webHookService) {
        this.webHookService = webHookService;
    }

    @GetMapping("/api/v1/events")
    public List<WebHookEvent> getAllEvents()
    {
        return webHookService.findAll();
    }


    @PostMapping("/api/v1/events")
    @ResponseStatus(HttpStatus.CREATED)
    public WebhookResponce createEvent(@RequestBody WebHookRequest request) {
        return webHookService.createEvent(request);
    }





}
