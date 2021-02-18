package com.example.notification.IMIconnectApi.service;

import com.example.notification.IMIconnectApi.model.ImiSmsRequest;
import org.springframework.stereotype.Service;

@Service
public interface SmsSender {
    public String smsSend(ImiSmsRequest imiSmsRequest);
}
