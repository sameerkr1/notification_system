package com.example.notification.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "sms_request")
@Table(name = "sms_request")
public class MessageModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "message")
    private String message;
    @Column(name = "status")
    private String status;
    @Column(name = "failure_code")
    private String failureCode;
    @Column(name = "failure_comment")
    private String failureComment;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "request_id")
    private String requestId;
}
