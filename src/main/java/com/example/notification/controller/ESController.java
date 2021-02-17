package com.example.notification.controller;

import com.example.notification.models.request.ESDateInputModel;
import com.example.notification.models.ESMessageModel;
import com.example.notification.service.ESServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ESController {


    @Autowired
    ESServiceManager ESServiceManager;


    @GetMapping("/search")
    public Page<ESMessageModel> allMessageBetweenGivenDates(@RequestBody ESDateInputModel eSDateInputModel) throws Exception {
        return ESServiceManager.allMessageBetweenStartDateAndEndDate(eSDateInputModel);
    }

    @GetMapping("/search/{text}")
    public Page<ESMessageModel> allMessageContainingGivenText(@PathVariable String text) throws Exception{
        try {
            return ESServiceManager.allMessageContainingGivenText(text);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Bad Request");
        }
    }
    @GetMapping("/findAll")
    public Page<ESMessageModel> findAllDocs(){
        return ESServiceManager.findAllDocs();
    }


}
