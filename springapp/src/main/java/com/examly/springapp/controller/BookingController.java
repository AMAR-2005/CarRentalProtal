package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Booking;
import com.examly.springapp.model.User;
import com.examly.springapp.service.BookingService;
import com.examly.springapp.service.UserService;

@RestController
@RequestMapping(path = "/api")
public class BookingController {
     @Autowired
     BookingService bookingService;

    @GetMapping(path = "/bookings")
    public List<Booking> getAllBooking(){
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
