package com.legely.webhooker.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name="events")
public class WebHookEvent {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false,updatable = false)
    private LocalDateTime timeSend;


    public WebHookEvent() {}
    public WebHookEvent(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @PrePersist
    public void prePersist() {
        this.timeSend = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeSend() {
        return timeSend;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }


}
