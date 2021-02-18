package com.example.notification.controller;


import com.example.notification.service.BlacklistServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BlacklistController {

    @Autowired
    BlacklistServiceManager blacklistServiceManager;


    @PostMapping("/blacklist/{phoneNumber}")
    public ResponseEntity<Object> addNumberToBlacklist(@PathVariable String phoneNumber){
        blacklistServiceManager.addNumberToBlacklist(phoneNumber);
        return new ResponseEntity<Object>("Successfully Added", HttpStatus.OK);
    }
    @GetMapping("/blacklist")
    public List<String> listOfBlacklistedNumber(){
        return blacklistServiceManager.listOfBlacklistedNumber();
    }

    @DeleteMapping("/remove/{phoneNumber}")
    public ResponseEntity<Object> removeNumberFromBlacklist(@PathVariable String phoneNumber){
        blacklistServiceManager.removeNumberFromBlacklist(phoneNumber);
        return new ResponseEntity<Object>("Successfully Removed",HttpStatus.OK);
    }

}
