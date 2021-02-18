package com.example.notification.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Success {
    private String id;
    private String phoneNumber;
    private String message;
}
