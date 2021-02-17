package com.example.notification.service;

import com.example.notification.models.request.ESDateInputModel;
import com.example.notification.models.ESMessageModel;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
public interface ESServiceManager {

    public Page<ESMessageModel> allMessageContainingGivenText(String text);
    public Page<ESMessageModel> allMessageBetweenStartDateAndEndDate(ESDateInputModel esDateInputModel) throws Exception;
    public Page<ESMessageModel> findAllDocs();

}
