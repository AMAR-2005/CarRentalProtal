package com.project.application.controller;

import com.project.application.model.Booking;
import com.project.application.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(path = "/bookings/ordered")
    public  List<Booking> getBookingOrderedByAmount(){
        return bookingService.orderByAmount();
    }

    @GetMapping(path = "/sortedBookings")
    public List<Booking> getSortedUsers(){
        return bookingService.getSortedBooking();
    }

    @PostMapping(path = "/bookings/add")
    public ResponseEntity<?> createBooking(@RequestParam int userId, @RequestParam int vehicleId,@Valid  @RequestBody Booking booking) {
        return bookingService.addBooking(userId, vehicleId, booking);
    }

    @PutMapping(path = "/bookings/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable int id,@Valid  @RequestBody Booking booking){
        return bookingService.updateBooking(id,booking);
    }
    @GetMapping(path = "/bookings/paginated")
    public Page<Booking> getUserPage(@RequestParam int page, @RequestParam int size){
        return bookingService.getPageBookings(page, size);
    }

    @DeleteMapping(path = "/bookings/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id){
        return bookingService.deleteBooking(id) ? ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully"): ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not deleted");
    }
    @GetMapping("/bookings/insurance")
    public List<Booking> getBookingsWithInsurance(@RequestParam boolean included) {
        return bookingService.getBookingsByInsuranceIncluded(included);
    }

    @PostMapping("/bookings/addMultiple")
    public ResponseEntity<?> addMultipleBookings(@RequestBody List<Booking> bookings) {
        return bookingService.addMultipleBooking(bookings);
    }
}
