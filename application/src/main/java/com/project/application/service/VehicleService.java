package com.project.application.service;

import com.project.application.model.RentalCompany;
import com.project.application.model.Vehicle;
import com.project.application.repository.RentalCompanyRepository;
import com.project.application.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    RentalCompanyRepository rentalCompanyRepository;
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(int id) {  
        return vehicleRepository.findById(id);
    }

    public ResponseEntity<?> createVehicle(int rentalCompanyId, Vehicle vehicle) {

        RentalCompany rentalCompany = rentalCompanyRepository.findById(rentalCompanyId).orElse(null);
        if (rentalCompany == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rental Company not found with ID: " + rentalCompanyId);
        }

        vehicle.setRentalCompany(rentalCompany);

        if (vehicle.getMake() == null || vehicle.getMake().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle make is required");
        }
        if (vehicle.getModel() == null || vehicle.getModel().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle model is required");
        }
        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle registration number is required");
        }
        if (vehicle.getRatePerDay() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rate per day must be greater than zero");
        }

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }

    public boolean deleteVehicle(int id) {  
        if(vehicleRepository.existsById(id)){
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean deleteVehicleByModel(String model) {

        if(vehicleRepository.deleteByModel(model)!=0)
                return true;
        return false;

    }
    public List<Vehicle> getSortedVehicles() {
        return vehicleRepository.findAll(Sort.by("ratePerDay").ascending());
    }

    public Vehicle updateVehicle(int id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id)
                .map(existingVehicle -> {
                    if (updatedVehicle.getMake() != null) existingVehicle.setMake(updatedVehicle.getMake());
                    if (updatedVehicle.getModel() != null) existingVehicle.setModel(updatedVehicle.getModel());
                    if (updatedVehicle.getRegistrationNumber() != null) existingVehicle.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
                    if (updatedVehicle.getRatePerDay() > 0) existingVehicle.setRatePerDay(updatedVehicle.getRatePerDay());
                    existingVehicle.setAvailable(updatedVehicle.isAvailable());
                    return vehicleRepository.save(existingVehicle);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with ID: " + id));
    }


    public Page<Vehicle> getPageVehicle(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return vehicleRepository.findAll(pageable);
    }

    public ResponseEntity<?> addMultipleVehicles(List<Vehicle> vehicles) {
        List<Vehicle> savedVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRentalCompany() == null || vehicle.getRentalCompany().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Each vehicle must have a valid rental company ID");
            }

            RentalCompany rentalCompany = rentalCompanyRepository.findById(vehicle.getRentalCompany().getId()).orElse(null);
            if (rentalCompany == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Rental Company not found with ID: " + vehicle.getRentalCompany().getId());
            }

            vehicle.setRentalCompany(rentalCompany);
            savedVehicles.add(vehicle);
        }

        return ResponseEntity.ok(vehicleRepository.saveAll(savedVehicles));
    }
    public List<Vehicle> getVehiclesByModel(String model) {

        return vehicleRepository.findBymodel(model);
    }
}
