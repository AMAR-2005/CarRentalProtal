package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Vehicle;
import com.examly.springapp.service.VehicleService;

@RestController
@RequestMapping("/api")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;

    @GetMapping(path = "vehicle")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable int id) {  
        
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        
        if (vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(vehicle.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle with ID " + id + " not found");
        }


    }

    @PutMapping(path = "/vehicle/{id}")
    public Vehicle updateVehicle(@PathVariable int id,@RequestBody Vehicle vehicle){
        return vehicleService.updateVehicle(id,vehicle);
    }

    @PostMapping(path = "/vehicle")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @DeleteMapping(path = "/vehicle/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {  
        
        return vehicleService.deleteVehicle(id) ? ResponseEntity.status(HttpStatus.OK).body("Vehicle deleted successfully"): ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle deletetion Failed");
    }


}
