package com.example.SMS.Repository;

import com.example.SMS.Models.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepo extends JpaRepository<FeedBack,Integer> {

}
