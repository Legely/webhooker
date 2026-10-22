package com.legely.webhooker.dto;

import java.time.LocalDateTime;

public record WebhookResponce(
        String sender,
        String message,
        LocalDateTime timeSend
) {}


