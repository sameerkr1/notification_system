package com.example.notification.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RedisServiceManger {
    void addNumberToBlacklist(String phoneNumber);
    void removeNumberFromBlacklist(String phoneNumber);
    Set findAllBlacklistedNumber();
    Boolean checkIfNumberIsBlackListedOrNot(String phoneNumber);
}
