package com.example.SMS.Repository;

import com.example.SMS.Models.ClassName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<ClassName,Integer> {

}
