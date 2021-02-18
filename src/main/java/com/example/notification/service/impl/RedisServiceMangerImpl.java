package com.example.notification.service.impl;

import com.example.notification.service.RedisServiceManger;
import com.example.notification.utils.Checker;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisServiceMangerImpl implements RedisServiceManger {

    private static final String KEY="BLACKLIST";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Checker checker;

    @Override
    public void addNumberToBlacklist(String phoneNumber) {
        redisTemplate.opsForSet().add(KEY,phoneNumber);
    }

    @Override
    public void removeNumberFromBlacklist(String phoneNumber) {
        redisTemplate.opsForSet().remove(KEY,phoneNumber);
    }

    @Override
    public Set findAllBlacklistedNumber() {
        return redisTemplate.opsForSet().members(KEY) ;
    }

    @Override
    public Boolean checkIfNumberIsBlackListedOrNot(String phoneNumber) {
        if(checker.validPhoneNumberChecker(phoneNumber)==true){
            return redisTemplate.opsForSet().isMember(KEY,phoneNumber);
        }
        return false;
    }
}
