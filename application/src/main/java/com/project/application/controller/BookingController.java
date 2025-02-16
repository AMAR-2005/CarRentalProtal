package com.project.application.controller;

import com.project.application.model.Booking;
import com.project.application.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class BookingController {
     @Autowired
     BookingService bookingService;

    @GetMapping(path = "/bookings")
    public List<com.project.application.model.Booking> getAllBooking(){
        return bookingService.getBooking();
    }

    @GetMapping(path = "/bookings/{id}")
    public Optional<Booking> getBookingById(@PathVariable int id){
        return bookingService.getBookingById(id);
    }
    @PostMapping(path = "/bookings")
    public Booking createBooking(@RequestBody Booking booking){
        return bookingService.addBooking(booking);
    }
    @PutMapping(path = "/bookings/{id}")
    public Booking updateBooking(@PathVariable int id, @RequestBody Booking booking){
        return bookingService.updateBooking(id,booking);
    }

    @DeleteMapping(path = "/bookings/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id){
        return bookingService.deleteBooking(id) ? ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully"): ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not deleted");
    }
}
