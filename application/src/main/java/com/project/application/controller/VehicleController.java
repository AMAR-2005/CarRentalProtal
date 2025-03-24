package com.project.application.controller;

import com.project.application.model.Vehicle;
import com.project.application.service.VehicleService;
import jakarta.validation.Valid;
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

    @GetMapping("/vehicle/by-model")
    public List<Vehicle> getVehiclesByModel(@RequestParam String model) {
        return vehicleService.getVehiclesByModel(model);
    }
    @PutMapping(path = "/vehicle/{id}")
    public Vehicle updateVehicle(@PathVariable int id,@Valid @RequestBody Vehicle vehicle){
        return vehicleService.updateVehicle(id,vehicle);
    }

    @PostMapping(path = "/vehicle/{rentalcompanyid}")
    public ResponseEntity<?> createVehicle(@PathVariable int rentalcompanyid,@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(rentalcompanyid,vehicle);
    }

    @PostMapping(path = "/vehicle/multi")
    public ResponseEntity<?> createBooking(@Valid @RequestBody List<Vehicle> vehicle){
        return vehicleService.addMultipleVehicles(vehicle);
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
