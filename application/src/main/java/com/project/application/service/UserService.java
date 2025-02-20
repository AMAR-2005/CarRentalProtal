package com.project.application.service;


import com.project.application.model.Booking;
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
//        List<Booking> bookingList = u.getBookings();
//        if(bookingList!=null){
//            for (Booking booking : bookingList) {
//                booking.setUser(u);
//            }
//        }
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
//        Optional<User> existingUserOpt = repo.findById(id);
//        if(existingUserOpt.isPresent()) {
//            User existingUser = existingUserOpt.get();
//            if(u.getName() != null) {
//                existingUser.setName(u.getName());
//            }
//            if(u.getEmail() != null) {
//                existingUser.setEmail(u.getEmail());
//            }
//            if(u.getPhone() != 0) {
//                existingUser.setPhone(u.getPhone());
//            }
//            if (u.getBookings() != null) {
//                for (Booking updatedBooking : u.getBookings()) {
//                    Optional<Booking> existingBookingOpt = existingUser.getBookings().stream().filter(b -> b.getId() == updatedBooking.getId()).findFirst();
//
//                    if (existingBookingOpt.isPresent()) {
//                        Booking existingBooking = existingBookingOpt.get();
//                        if (updatedBooking.getStartDate() != null) {
//                            existingBooking.setStartDate(updatedBooking.getStartDate());
//                        }
//                        if (updatedBooking.getEndDate() != null) {
//                            existingBooking.setEndDate(updatedBooking.getEndDate());
//                        }
//                        if (updatedBooking.getTotalAmount() > 0) {
//                            existingBooking.setTotalAmount(updatedBooking.getTotalAmount());
//                        }
//                        existingBooking.setInsuranceIncluded(updatedBooking.isInsuranceIncluded());
//                    } else {
//                        throw new RuntimeException("Booking not found: " + updatedBooking.getId());
//                    }
//                }
//            }
            return repo.save(u);
//        } else {
//            throw new RuntimeException("User not found: " + id);
//        }
    }
    
    public Page<User> getPageUsers(int page,int size){
            Pageable pageable=PageRequest.of(page, size,Sort.by("name").ascending());
            return repo.findAll(pageable);
    }

    public List<User> searchNameContained(String name){
        return repo.findNameContained(name);
    }

    public List<User> getUsersByName(String name) {
        return repo.findByname(name);
    }
    public List<User> addMultipleUsers(List<User> user) {
        return repo.saveAll(user);
    }
}
