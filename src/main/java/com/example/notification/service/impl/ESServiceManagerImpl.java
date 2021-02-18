package com.example.notification.service.impl;

import com.example.notification.dao.repo.CustomESRepository;
import com.example.notification.exceptions.ResourceNotFoundException;
import com.example.notification.models.request.ESDateInputModel;
import com.example.notification.models.ESMessageModel;
import com.example.notification.service.ESServiceManager;
import com.example.notification.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
public class ESServiceManagerImpl implements ESServiceManager {

    @Autowired
    CustomESRepository customESRepository;
    @Autowired
    DateConverter dateConverter;

    @Override
    public Page<ESMessageModel> allMessageContainingGivenText(String text){
        Page<ESMessageModel> page = customESRepository.findByMessage(text, PageRequest.of(0,2));
        if(page==null){
            throw new ResourceNotFoundException("No such resource found");
        }{
            return page;
        }
    }

    @Override
    public Page<ESMessageModel> allMessageBetweenStartDateAndEndDate(ESDateInputModel eSDateInputModel) throws Exception{
        try {
            long startDate,endDate;
            startDate = dateConverter.converter(eSDateInputModel.getInputDate());
            endDate = dateConverter.converter(eSDateInputModel.getOutputDate());
            return customESRepository.findAllByCreatedAtBetween(startDate,endDate, PageRequest.of(0,2));
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Bad Request");
        }
    }
    @Override
    public Page<ESMessageModel> findAllDocs(){
        Page<ESMessageModel> page = customESRepository.findAll();
        if(page == null){
            throw new ResourceNotFoundException("resource not found");
        }
        else return page;
    }
}
