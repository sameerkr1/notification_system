package com.example.notification.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity(name = "blacklist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blacklist")
public class BlacklistModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "is_number_black_listed")
    private boolean isNumberBlackListed;
}
