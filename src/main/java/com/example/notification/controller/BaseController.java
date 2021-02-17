package com.example.notification.controller;

import com.example.notification.models.MessageModel;
import com.example.notification.service.MessageService;
import com.example.notification.models.response.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public class BaseController {
    @Autowired
    MessageService messageService;

    @PostMapping("/sms/send")
    public ResponseEntity<Object> saveMessage(@RequestBody MessageModel messageModel){
        Success success = new Success();
        success = messageService.saveMessage(messageModel);
        return new ResponseEntity<Object>(success,HttpStatus.OK);
    }

    @GetMapping("/sms/{requestId}")
    public Success getMessageByRequestId(@PathVariable String requestId){
        return messageService.getMessageByRequestId(requestId);
    }

}
