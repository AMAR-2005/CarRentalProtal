package com.project.application.service;

import com.project.application.model.Vehicle;
import com.project.application.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(int id) {  
        return vehicleRepository.findById(id);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public boolean deleteVehicle(int id) {  
        if(vehicleRepository.existsById(id)){
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Vehicle updateVehicle(int id, Vehicle vehicle) {
       vehicle.setId(id);
       return vehicleRepository.save(vehicle);
    }


}
