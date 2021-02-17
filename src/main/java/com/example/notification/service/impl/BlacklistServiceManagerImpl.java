package com.example.notification.service.impl;

import com.example.notification.dao.repo.BlacklistRepository;
import com.example.notification.dao.repo.MessageRepository;
import com.example.notification.exceptions.InvalidRequestException;
import com.example.notification.models.BlacklistModel;
import com.example.notification.service.BlacklistServiceManager;
import com.example.notification.service.RedisServiceManger;
import com.example.notification.utils.Checker;
import com.example.notification.utils.SaveToESDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlacklistServiceManagerImpl implements BlacklistServiceManager {

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
    public void addNumberToBlacklist(String phoneNumber){
        if(checker.validPhoneNumberChecker(phoneNumber)==false){
            throw new InvalidRequestException("Invalid Phone Number");
        }
        if(checker.blacklistChecker(phoneNumber)==true){
            throw new InvalidRequestException("Number is already blacklisted");
        }
        try {
            if(blacklistRepository.existsByPhoneNumber(phoneNumber)==true){
                BlacklistModel blacklistModel = blacklistRepository.findByPhoneNumber(phoneNumber);
                blacklistModel.setNumberBlackListed(true);
                blacklistRepository.save(blacklistModel);
                redisServiceManger.addNumberToBlacklist(phoneNumber);
            }
            else {
                BlacklistModel blacklistModel = new BlacklistModel();
                blacklistModel.setNumberBlackListed(true);
                blacklistModel.setPhoneNumber(phoneNumber);
                blacklistRepository.save(blacklistModel);
                redisServiceManger.addNumberToBlacklist(phoneNumber);
            }
        }
        catch (InvalidRequestException e){
            throw new InvalidRequestException("Invalid request");
        }
    }
    @Override
    public List<String> listOfBlacklistedNumber(){
        try {
            List<BlacklistModel> blacklistModel = blacklistRepository.findByIsNumberBlackListed(true);
            List<String> blacklistedNumbers = new ArrayList<String>();

            for(BlacklistModel element : blacklistModel) {
                blacklistedNumbers.add(element.getPhoneNumber());
            }
            return blacklistedNumbers;
        }
        catch (InvalidRequestException e){
            e.printStackTrace();
            throw new InvalidRequestException("Invalid Request");
        }
    }

    @Override
    public void removeNumberFromBlacklist(String phoneNumber){
        if(checker.validPhoneNumberChecker(phoneNumber)==false){
            throw new InvalidRequestException("Invalid Phone Number");
        }
        if(checker.blacklistChecker(phoneNumber)==false){
            throw new InvalidRequestException("Number is not blacklisted");
        }
        try{
           if(redisServiceManger.checkIfNumberIsBlackListedOrNot(phoneNumber)==true){
                 redisServiceManger.removeNumberFromBlacklist(phoneNumber);
           }
           try{
                BlacklistModel blacklistModel = blacklistRepository.findByPhoneNumber(phoneNumber);
                blacklistModel.setNumberBlackListed(false);
                blacklistRepository.save(blacklistModel);
           }
           catch (NullPointerException e){
                e.printStackTrace();
           }
        }
        catch (InvalidRequestException e){
            throw new InvalidRequestException("Invalid request");
        }
    }
}
