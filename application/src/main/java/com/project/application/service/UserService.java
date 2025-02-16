package com.project.application.service;


import com.project.application.model.User;
import com.project.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository repo;

    public List<User> getAllUsers() {
       return repo.findAll();
    }

    public List<User> getSortedUsers() {
    //    return repo.findAll(Sort.by(Sort.Direction.ASC,"name"));
       return repo.findAll(Sort.by("name").ascending());
    }

    public User createUser(User u) {
        return repo.save(u);
    }

    public Optional<User> getUsersById(int id) {
        return repo.findById(id);
    }

    public boolean deleteUser(int id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
    
    public User updateUser(int id, User u) {
            u.setId(id);
            return repo.save(u);
    }
    
    public Page<User> getPageUsers(int page,int size){
            Pageable pageable=PageRequest.of(page, size,Sort.by("name").ascending());
            return repo.findAll(pageable);
    }

    public List<User> searchNameContained(String name){
        return repo.findNameContained(name);
    }


}
