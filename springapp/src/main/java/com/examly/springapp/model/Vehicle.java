package com.examly.springapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "vehicle_id")
    @SequenceGenerator(name = "vehicle_id",allocationSize = 1)
    private int id;  
    private String make;
    private String model;
    private String registrationNumber;
    private double ratePerDay;
    private boolean available;
    // private RentalCompany rentalCompany;

}
