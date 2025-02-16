package com.project.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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


//    public Vehicle() {
//    }
//
//    public Vehicle(int id, String make, String model, String registrationNumber, double ratePerDay, boolean available) {
//        this.id = id;
//        this.make = make;
//        this.model = model;
//        this.registrationNumber = registrationNumber;
//        this.ratePerDay = ratePerDay;
//        this.available = available;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getMake() {
//        return make;
//    }
//
//    public void setMake(String make) {
//        this.make = make;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getRegistrationNumber() {
//        return registrationNumber;
//    }
//
//    public void setRegistrationNumber(String registrationNumber) {
//        this.registrationNumber = registrationNumber;
//    }
//
//    public double getRatePerDay() {
//        return ratePerDay;
//    }
//
//    public void setRatePerDay(double ratePerDay) {
//        this.ratePerDay = ratePerDay;
//    }
//
//    public boolean isAvailable() {
//        return available;
//    }
//
//    public void setAvailable(boolean available) {
//        this.available = available;
//    }
}
