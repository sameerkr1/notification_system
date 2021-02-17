package com.example.notification.utils;

import com.example.notification.dao.repo.CustomESRepository;
import com.example.notification.models.ESMessageModel;
import com.example.notification.models.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaveToESDatabase {

    @Autowired
    CustomESRepository customESRepository;

    public void save(MessageModel messageModel){
        ESMessageModel ESMessageModel = new ESMessageModel();
        ESMessageModel.setMessage(messageModel.getMessage());
        ESMessageModel.setId(messageModel.getRequestId());
        ESMessageModel.setPhoneNumber(messageModel.getPhoneNumber());
        ESMessageModel.setCreatedAt(new Date());
        customESRepository.save(ESMessageModel);
    }
}
