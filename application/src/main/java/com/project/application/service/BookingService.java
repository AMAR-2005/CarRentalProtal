package com.project.application.service;


import com.project.application.model.Booking;
import com.project.application.model.User;
import com.project.application.model.Vehicle;
import com.project.application.repository.BookingRepository;
import com.project.application.repository.UserRepository;
import com.project.application.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    public List<Booking> getBooking() {
        return bookingRepo.findAll();
    }

    public Optional<Booking> getBookingById(int id) {
        return bookingRepo.findById(id);
    }

    public Booking addBooking(int id,Booking booking) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
        booking.setUser(user);
        return bookingRepo.save(booking);
    }

    public Booking updateBooking(int id, Booking updatedBooking) {
        Booking existingBooking = bookingRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found: " + id));
        if (updatedBooking.getStartDate() != null) {
            existingBooking.setStartDate(updatedBooking.getStartDate());
        }
        if (updatedBooking.getEndDate() != null) {
            existingBooking.setEndDate(updatedBooking.getEndDate());
        }
        if (updatedBooking.getTotalAmount() != 0) {
            existingBooking.setTotalAmount(updatedBooking.getTotalAmount());
        }
        existingBooking.setInsuranceIncluded(updatedBooking.isInsuranceIncluded());
        existingBooking.setUser(existingBooking.getUser());
        return bookingRepo.save(existingBooking);
    }
    public List<Booking> orderByAmount(){
        return bookingRepo.orderByAmount();
    }

    public List<Booking> getSortedBooking() {
        return bookingRepo.findAll(Sort.by("startDate").ascending());
    }

    public boolean deleteBooking(int id) {
        if (!bookingRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found: " + id);
        }
        bookingRepo.deleteById(id);
        return true;
    }

    public Page<Booking> getPageBookings(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        return bookingRepo.findAll(pageable);
    }
    public List<Booking> addMultipleBooking(List<Booking> booking) {
        return bookingRepo.saveAll(booking);
    }
    public List<Booking> getBookingsByInsuranceIncluded(boolean insuranceIncluded) {
        return bookingRepo.findByinsuranceIncluded(insuranceIncluded);
    }
}
