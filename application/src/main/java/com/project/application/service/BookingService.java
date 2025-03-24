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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    public ResponseEntity<?> addBooking(int userId, int vehicleId, Booking booking) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with ID: " + userId);
        }

        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vehicle not found with ID: " + vehicleId);
        }

        long daysBetween = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
        if (daysBetween <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("End date must be after start date");
        }

        if (!vehicle.isAvailable()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Vehicle is not available for booking");
        }

        double totalAmount = daysBetween * vehicle.getRatePerDay();
        booking.setUser(user);
        booking.setVehicle(vehicle);
        booking.setTotalAmount(totalAmount);
        vehicle.setAvailable(false);

        Booking savedBooking = bookingRepo.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }


    public ResponseEntity<?> updateBooking(int id, Booking updatedBooking) {

        Booking existingBooking = bookingRepo.findById(id).orElse(null);
        if (existingBooking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Booking not found with ID: " + id);
        }

        long days = ChronoUnit.DAYS.between(updatedBooking.getStartDate(), updatedBooking.getEndDate());
        if (days <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("End date must be after start date.");
        }

        double ratePerDay = existingBooking.getVehicle().getRatePerDay();
        double newTotalAmount = days * ratePerDay;

        existingBooking.setStartDate(updatedBooking.getStartDate());
        existingBooking.setEndDate(updatedBooking.getEndDate());
        existingBooking.setInsuranceIncluded(updatedBooking.isInsuranceIncluded());
        existingBooking.setTotalAmount(newTotalAmount);

        Booking savedBooking = bookingRepo.save(existingBooking);
        return ResponseEntity.ok(savedBooking);
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

    public List<Booking> getBookingsByInsuranceIncluded(boolean insuranceIncluded) {
        return bookingRepo.findByinsuranceIncluded(insuranceIncluded);
    }

    public ResponseEntity<?> addMultipleBooking(List<Booking> bookings) {
        List<Booking> savedBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            User user = userRepository.findById(booking.getUser().getId()).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found with ID: " + booking.getUser().getId());
            }
            Vehicle vehicle = vehicleRepository.findById(booking.getVehicle().getId()).orElse(null);
            if (vehicle == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vehicle not found with ID: " + booking.getVehicle().getId());
            }
            long daysBetween = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
            if (daysBetween <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("End date must be after start date");
            }

            double totalAmount = daysBetween * vehicle.getRatePerDay();

            if (!vehicle.isAvailable()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle is not available for booking");

            }

            booking.setUser(user);
            booking.setVehicle(vehicle);
            booking.setTotalAmount(totalAmount);
            vehicle.setAvailable(false);
            savedBookings.add(booking);
        }

        return ResponseEntity.ok(bookingRepo.saveAll(savedBookings));
    }

}
