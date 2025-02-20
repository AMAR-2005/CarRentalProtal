package com.project.application.controller;


import com.project.application.model.Booking;
import com.project.application.model.RentalCompany;
import com.project.application.model.User;
import com.project.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    UserService obj;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return obj.getAllUsers();
    }

    @GetMapping("/users/by-name")
    public List<User> getUsersByName(@RequestParam String name) {
        return obj.getUsersByName(name);
    }
    @GetMapping(path = "/users/{id}")
    public Optional<User> getUserById(@PathVariable int id){
        return obj.getUsersById(id);
    }
    @PostMapping(path = "/users")
    public User createUser(@RequestBody User u){
        return obj.createUser(u);
    }
    @PostMapping(path = "/users/mutil")
    public List<User> createBooking(@RequestBody List<User> user){
        return obj.addMultipleUsers(user);
    }
    @PutMapping(path = "/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User u){
        return obj.updateUser(id,u);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
       return obj.deleteUser(id) ? ResponseEntity.status(HttpStatus.OK).body("User deleted successfully") :ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not deleted"); 
    }

    @GetMapping(path = "/getUser")
    public Page<User> getUserPage(@RequestParam int page,@RequestParam int size){
        return obj.getPageUsers(page, size);
    }

    @GetMapping(path = "/sortedUsers")
    public List<User> getSortedUsers(){
        return obj.getSortedUsers();
    }

    @GetMapping(path = "/search/{name}")
    public List<User> findNameContained(@PathVariable String name){
        return obj.searchNameContained(name);
    }

}
