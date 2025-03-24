package com.project.application.service;

import com.project.application.model.Booking;
import com.project.application.model.Payment;
import com.project.application.repository.BookingRepository;
import com.project.application.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    BookingRepository bookingRepository;

    public ResponseEntity<?> createPayment(int bookingId, Payment payment) {

        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Booking not found with ID: " + bookingId);
        }

        payment.setBooking(booking);
        payment.setAmount(booking.getTotalAmount());

        Payment savedPayment = paymentRepository.save(payment);
        return ResponseEntity.ok(savedPayment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found: " + id));
    }

    public Payment updatePayment(int id, Payment updatedPayment) {
        Payment existingPayment = getPaymentById(id);
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
        existingPayment.setTransactionId(updatedPayment.getTransactionId());
        existingPayment.setPaymentStatus(updatedPayment.getPaymentStatus());
        existingPayment.setAmount(updatedPayment.getAmount());
        return paymentRepository.save(existingPayment);
    }

}
