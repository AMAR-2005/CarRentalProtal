package com.project.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_id")
    @SequenceGenerator(name = "vehicle_id", allocationSize = 1)
    private int id;

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @Column(name = "registration_number", unique = true)
    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @Positive(message = "Rate per day must be greater than 0")
    private double ratePerDay;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "rentalcompany_id", nullable = false)
    @JsonIgnoreProperties("vehicle")
    private RentalCompany rentalCompany;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("vehicle")
    private List<Booking> bookings;

}