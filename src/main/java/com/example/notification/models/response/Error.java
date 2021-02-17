package com.example.notification.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Error {
    private Date timestamp;
    private String code;
    private String message;
    private String details;
}
