package com.example.notification.service.impl;

import com.example.notification.models.MessageModel;
import com.example.notification.exceptions.InvalidRequestException;
import com.example.notification.exceptions.ResourceNotFoundException;
import com.example.notification.dao.repo.BlacklistRepository;
import com.example.notification.dao.repo.MessageRepository;
import com.example.notification.service.MessageService;
import com.example.notification.service.RedisServiceManger;
import com.example.notification.utils.Checker;
import com.example.notification.utils.SaveToESDatabase;
import com.example.notification.models.response.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Component
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private BlacklistRepository blacklistRepository;
    @Autowired
    private KafkaProducerServiceImpl kafkaProducerServiceImpl;
    @Autowired
    private RedisServiceManger redisServiceManger;
    @Autowired
    private SaveToESDatabase saveToESDatabase;
    @Autowired
    private Checker checker;


    @Override
    public Success saveMessage(MessageModel messageModel) {
        if(checker.validPhoneNumberChecker(messageModel.getPhoneNumber()) == false){
            throw new InvalidRequestException("Phone number is invalid");
        }
        if(messageModel.getMessage()==null){
            throw new InvalidRequestException("Message Should not be null");
        }
        if(checker.blacklistChecker(messageModel.getPhoneNumber())==true){
            throw new InvalidRequestException("Sorry the number is blacklisted");
        }
        try {
            messageModel.setRequestId(UUID.randomUUID().toString());
            messageRepository.save(messageModel);
            messageModel.setStatus("Queued");
            saveToESDatabase.save(messageModel);
            kafkaProducerServiceImpl.sendMessage(messageModel);
            Success success = new Success(messageModel.getRequestId(),messageModel.getPhoneNumber(),messageModel.getMessage());
            return success;
        }
        catch (NullPointerException e){
            throw new NullPointerException("Invalid request");
        }
    }
    @Override
    public Success getMessageByRequestId(String requestId) {
        if(messageRepository.existsByRequestId(requestId)==false){
            throw new ResourceNotFoundException("sorry Requested id is not found");
        }
        MessageModel messageModel = messageRepository.findByRequestId(requestId);
        if(checker.blacklistChecker(messageModel.getPhoneNumber())){
            throw new InvalidRequestException("Sorry the number is blacklisted");
        }
        if(messageModel == null){
            throw new ResourceNotFoundException("RequestedId is not available");
        }
        try{
            return new Success(messageModel.getId().toString(),messageModel.getPhoneNumber(),messageModel.getMessage());
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Required resource is not available");
        }
    }

}
