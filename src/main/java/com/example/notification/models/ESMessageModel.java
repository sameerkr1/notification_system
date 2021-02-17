package com.example.notification.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Document(indexName = "my_index")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ESMessageModel {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "phone_number")
    private String phoneNumber;
    @Field(type = FieldType.Text, name = "message")
    private String message;
    @Field(type = FieldType.Date, name = "created_at")
    private Date createdAt;
}
