package com.example.notification.service.impl;

import com.example.notification.IMIconnectApi.model.Channels;
import com.example.notification.IMIconnectApi.model.Destination;
import com.example.notification.IMIconnectApi.model.ImiSmsRequest;
import com.example.notification.IMIconnectApi.model.Sms;
import com.example.notification.IMIconnectApi.response.ExternalApiResponse;
import com.example.notification.IMIconnectApi.service.SmsSender;
import com.example.notification.dao.repo.MessageRepository;
import com.example.notification.models.MessageModel;
import com.example.notification.service.KafkaConsumerService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    SmsSender smsSender;


    @KafkaListener(topics = "notification.send_sms",groupId = "groupId")
    public void consumeMessage(String requestedId) throws Exception {
        try {
            MessageModel messageObject = messageRepository.findByRequestId(requestedId);

            List<String> phoneNumber = new ArrayList<>();
            phoneNumber.add(messageObject.getPhoneNumber());
            Sms sms=Sms.builder().text(messageObject.getMessage()).build();
            Channels channels = Channels.builder().sms(sms).build();
            Destination destination = Destination.builder()
                    .msisdn(phoneNumber)
                    .coRelationId(messageObject.getRequestId())
                    .build();

            List<Destination> destinationList = new ArrayList<>();
            destinationList.add(destination);
            ImiSmsRequest imiSmsRequest = ImiSmsRequest.builder()
                    .channels(channels).deliveryChannel("sms")
                    .destinations(destinationList)
                    .build();

            String response = smsSender.smsSend(imiSmsRequest);
            Gson gson = new Gson();
            ExternalApiResponse externalApiResponse = gson.fromJson(response,ExternalApiResponse.class);


            System.out.println(externalApiResponse.getCode());
        }
        catch (Exception e){
            throw new Exception("Bad request");
        }
    }
}
