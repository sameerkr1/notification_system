package com.example.notification.service;

import com.example.notification.models.MessageModel;
import com.example.notification.models.response.Success;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MessageService {
    public Success saveMessage(MessageModel messageModel);
    public Success getMessageByRequestId(String id);
}
