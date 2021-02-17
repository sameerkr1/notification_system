package com.example.notification.utils;

import com.example.notification.dao.repo.BlacklistRepository;
import com.example.notification.service.RedisServiceManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Checker {
    @Autowired
    RedisServiceManger redisServiceManger;

    @Autowired
    BlacklistRepository blacklistRepository;


    public boolean blacklistChecker(String phoneNumber){
        if(redisServiceManger.checkIfNumberIsBlackListedOrNot(phoneNumber)==true){
            return true;
        }
        else{
            if(blacklistRepository.existsByPhoneNumber(phoneNumber)){
                return blacklistRepository.findByPhoneNumber(phoneNumber).isNumberBlackListed();
            }
            else return false;
        }
    }

    public boolean validPhoneNumberChecker(String s)
    {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
}
