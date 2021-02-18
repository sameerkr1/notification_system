package com.example.notification.dao.repo;

import com.example.notification.models.ESMessageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CustomESRepository extends ElasticsearchRepository<ESMessageModel,String> {
    Page<ESMessageModel> findByMessage(String text, Pageable pageable);
    Page<ESMessageModel> findAllByCreatedAtBetween(long startDate, long endDate, Pageable pageable);
    Page<ESMessageModel> findAll();
}
