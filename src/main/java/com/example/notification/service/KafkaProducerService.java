package com.example.notification.service;

import com.example.notification.models.MessageModel;
import org.springframework.stereotype.Service;

@Service
public interface KafkaProducerService {
    public void sendMessage(MessageModel messageModel);
}
