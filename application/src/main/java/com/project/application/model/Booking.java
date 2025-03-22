package com.project.application.model;import com.fasterxml.jackson.annotation.*;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import java.time.LocalDate;@Entity@Data@AllArgsConstructor@NoArgsConstructorpublic class Booking {    @Id    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "booking_id")    @SequenceGenerator(name = "booking_id",allocationSize = 1)    private int id;    @ManyToOne    @JoinColumn(name = "user_id", nullable = false)    @JsonIgnoreProperties("bookings")    private User user;    private LocalDate startDate;    private LocalDate endDate;    private int totalAmount;    private boolean insuranceIncluded;}