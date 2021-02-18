package com.example.notification.service;

import org.springframework.stereotype.Service;

@Service
public interface KafkaConsumerService {
    public void consumeMessage(String requestedId) throws Exception;
}
