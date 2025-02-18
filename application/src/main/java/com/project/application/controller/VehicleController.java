package com.project.application.controller;


import com.project.application.model.Booking;
import com.project.application.model.User;
import com.project.application.model.Vehicle;
import com.project.application.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping(path = "/vehicle/paginated")
    public Page<Vehicle> getUserPage(@RequestParam int page, @RequestParam int size){
        return vehicleService.getPageVehicle(page, size);
    }
    @GetMapping(path = "/sortedVehicle")
    public List<Vehicle> getSortedUsers(){
        return vehicleService.getSortedVehicles();
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
    @DeleteMapping(path = "/vehicle")
    public ResponseEntity<?> deleteVehicleByModel(@RequestParam String model) {
        return vehicleService.deleteVehicleByModel(model) ? ResponseEntity.status(HttpStatus.OK).body("Vehicle deleted successfully"): ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle deletetion Failed");
    }


}
