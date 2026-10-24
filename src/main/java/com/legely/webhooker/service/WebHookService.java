package com.legely.webhooker.service;


import com.legely.webhooker.repository.WebHookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WebHookService {

    WebHookRepository repository;
    public WebHookService(WebHookRepository repository) {
        this.repository = repository;
    }

    @Transactional

}
