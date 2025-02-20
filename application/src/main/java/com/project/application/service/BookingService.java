package com.project.application.service;


import com.project.application.model.Booking;
import com.project.application.model.RentalCompany;
import com.project.application.model.User;
import com.project.application.repository.BookingRepository;
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
public class BookingService {
    @Autowired
    BookingRepository bookingRepo;
    @Autowired
    UserRepository userRepository;

    public List<Booking> getBooking() {
        return bookingRepo.findAll();
    }

    public Optional<Booking> getBookingById(int id) {
        return bookingRepo.findById(id);
    }

//    public Booking addBooking(int id,Booking booking) {
////        Optional<User> opt= userRepository.findById(id);
////        if(!opt.isEmpty()){
////            User user=opt.get();
////            booking.setUser(user);
//            return bookingRepo.save(booking);
////        }
////        else{
////            throw new RuntimeException("User Not found :"+id);
////        }
//    }

    public Booking updateBooking(int id, Booking booking) {
        if(bookingRepo.existsById(id)){
            booking.setId(id);
            return bookingRepo.save(booking);
        }
        return null;
    }
    public List<Booking> orderByAmount(){
        return bookingRepo.orderByAmount();
    }
    public List<Booking> getSortedBooking() {
        return bookingRepo.findAll(Sort.by("startDate").ascending());
    }

    public boolean deleteBooking(int id) {
        if(bookingRepo.existsById(id)){
            bookingRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Booking> getPageBookings(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        return bookingRepo.findAll(pageable);
    }

    public Booking addBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

    public List<Booking> addMultipleBooking(List<Booking> booking) {
        return bookingRepo.saveAll(booking);
    }
    public List<Booking> getBookingsByInsuranceIncluded(boolean insuranceIncluded) {
        return bookingRepo.findByinsuranceIncluded(insuranceIncluded);
    }
}
