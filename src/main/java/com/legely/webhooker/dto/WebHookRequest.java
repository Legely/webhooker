package com.legely.webhooker.dto;

public record WebHookRequest (
        String sender,
        String message
){}
