package com.project.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @NotNull(message = "Booking is required")
    @JsonIgnoreProperties({"payment", "user", "vehicle"})
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;

    @Positive(message = "Amount must be greater than 0")
    private double amount;


}

enum PaymentMethod { CREDIT_CARD, DEBIT_CARD, PAYPAL, CASH }
enum PaymentStatus { COMPLETED, FAILED, PENDING }

