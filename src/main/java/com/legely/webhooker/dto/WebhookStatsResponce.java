package com.legely.webhooker.dto;

import java.time.LocalDateTime;

public record WebhookStatsResponce(
        String sender,
        String message,
        LocalDateTime timeSend
) {}