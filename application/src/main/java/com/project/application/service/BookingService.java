package com.project.application.service;


import com.project.application.model.Booking;
import com.project.application.model.RentalCompany;
import com.project.application.repository.BookingRepository;
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

    public List<Booking> getBooking() {
        return bookingRepo.findAll();
    }

    public Optional<Booking> getBookingById(int id) {
        return bookingRepo.findById(id);
    }

    public Booking addBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

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
}
