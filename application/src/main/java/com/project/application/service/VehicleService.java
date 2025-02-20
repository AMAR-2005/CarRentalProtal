package com.project.application.service;

import com.project.application.model.User;
import com.project.application.model.Vehicle;
import com.project.application.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public boolean deleteVehicleByModel(String model) {

        if(vehicleRepository.deleteByModel(model)!=0)
                return true;
        return false;

    }
    public List<Vehicle> getSortedVehicles() {
        return vehicleRepository.findAll(Sort.by("ratePerDay").ascending());
    }

    public Vehicle updateVehicle(int id, Vehicle vehicle) {
       vehicle.setId(id);
       return vehicleRepository.save(vehicle);
    }


    public Page<Vehicle> getPageVehicle(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return vehicleRepository.findAll(pageable);
    }

    public List<Vehicle> addMultipleVehicle(List<Vehicle> vehicle) {
        return vehicleRepository.saveAll(vehicle);
    }
    public List<Vehicle> getVehiclesByModel(String model) {
        return vehicleRepository.findBymodel(model);
    }
}
