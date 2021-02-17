package com.example.notification.service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlacklistServiceManager {
    public void addNumberToBlacklist(String phoneNumber);
    public List<String> listOfBlacklistedNumber();
    public void removeNumberFromBlacklist(String phoneNumber);
}
