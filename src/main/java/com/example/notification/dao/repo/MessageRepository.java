package com.example.notification.dao.repo;

import com.example.notification.models.MessageModel;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MessageRepository extends JpaRepository<MessageModel, Integer> {
    public MessageModel findByRequestId(String requestId);
    public boolean existsByRequestId(String requestId);
}
