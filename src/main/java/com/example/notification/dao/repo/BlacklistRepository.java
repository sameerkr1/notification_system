package com.example.notification.dao.repo;

import com.example.notification.models.BlacklistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<BlacklistModel,Integer> {

    public Boolean existsByPhoneNumber(String phoneNumber);
    public BlacklistModel findByPhoneNumber(String phoneNumber);
    public void deleteByPhoneNumber(String phoneNumber);
    public List<BlacklistModel> findByIsNumberBlackListed(Boolean b);


}