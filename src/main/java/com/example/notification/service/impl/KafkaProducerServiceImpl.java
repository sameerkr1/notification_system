package com.example.notification.service.impl;

import com.example.notification.models.MessageModel;
import com.example.notification.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    @Value("${kafka.topic}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(MessageModel messageModel){
        kafkaTemplate.send(TOPIC,messageModel.getRequestId());
    }
}
