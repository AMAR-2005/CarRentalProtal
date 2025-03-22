package com.project.application.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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
    private String make;
    private String model;
    @Column(name = "registration_number")
    private String registrationNumber;
    private double ratePerDay;
    private boolean available;

}