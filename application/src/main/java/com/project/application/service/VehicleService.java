package com.project.application.service;

import com.project.application.model.Booking;
import com.project.application.model.RentalCompany;
import com.project.application.model.User;
import com.project.application.model.Vehicle;
import com.project.application.repository.RentalCompanyRepository;
import com.project.application.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Vehicle createVehicle(int rentalCompanyId,Vehicle vehicle) {
        RentalCompany rentalCompany = rentalCompanyRepository.findById(rentalCompanyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental Company not found with ID: " + rentalCompanyId));

        // Associate the vehicle with the rental company
        vehicle.setRentalCompany(rentalCompany);

        // Validate required fields
        if (vehicle.getMake() == null || vehicle.getMake().isEmpty()) {
            throw new IllegalArgumentException("Vehicle make is required");
        }
        if (vehicle.getModel() == null || vehicle.getModel().isEmpty()) {
            throw new IllegalArgumentException("Vehicle model is required");
        }
        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number is required");
        }
        if (vehicle.getRatePerDay() <= 0) {
            throw new IllegalArgumentException("Rate per day must be greater than zero");
        }

        return vehicleRepository.save(vehicle);
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

    public List<Vehicle> addMultipleVehicle(int rentalcompanyid,List<Vehicle> vehicle) {
        RentalCompany rentalCompany = rentalCompanyRepository.findById(rentalcompanyid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental Company not found with ID: " + rentalcompanyid));

        for (Vehicle veh : vehicle) {
            veh.setRentalCompany(rentalCompany);
        }
        return vehicleRepository.saveAll(vehicle);
    }
    public List<Vehicle> getVehiclesByModel(String model) {

        return vehicleRepository.findBymodel(model);
    }
}
