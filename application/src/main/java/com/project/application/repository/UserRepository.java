package com.project.application.repository;


import com.project.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    List<User> findByname(String name);
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name% ")
    List<User> findNameContained(@Param("name") String name);
}
