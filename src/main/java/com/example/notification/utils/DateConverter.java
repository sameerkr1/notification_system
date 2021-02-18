package com.example.notification.utils;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateConverter {
    public long converter(String stringDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yy hh:mm:ss");
        Date date = null;

        try{
            date = simpleDateFormat.parse(stringDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        long epoch = date.getTime();
        return epoch;
    }
}
