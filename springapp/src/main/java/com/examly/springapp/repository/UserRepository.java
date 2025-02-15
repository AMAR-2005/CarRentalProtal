package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name% ")
    List<User> findNameContained(@Param("name") String name);
}
