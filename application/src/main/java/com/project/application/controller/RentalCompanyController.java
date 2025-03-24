package com.project.application.controller;


import com.project.application.model.RentalCompany;
import com.project.application.service.RentalCompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class RentalCompanyController {
    
    @Autowired
    RentalCompanyService rentalCompanyService;

    
    @GetMapping(path = "/rentalcompanies")
    public List<RentalCompany> getAllRentalCompanies() {
        return rentalCompanyService.getAllRentalCompanies();
    }

    @GetMapping("/rentalcompanies/by-name")
    public List<RentalCompany> getRentalCompaniesByName(@RequestParam String name) {
        return rentalCompanyService.getRentalCompaniesByName(name);
    }

    @GetMapping(path = "/rentalcompanies/{id}")
    public ResponseEntity<?> getRentalCompanyById(@PathVariable int id) {
        Optional<RentalCompany> rentalCompany = rentalCompanyService.getRentalCompanyById(id);
        if (rentalCompany.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(rentalCompany.get()); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rental Company with ID " + id + " not found"); 
        }
    }
    @GetMapping(path = "/rentalcompanies/sortedRental")
    public List<RentalCompany> getSortedUsers(){
        return rentalCompanyService.getSortedRentalCompanies();
    }

    @PostMapping(path = "/rentalcompanies")
    public RentalCompany createRentalCompany(@Valid @RequestBody RentalCompany rentalCompany) {
        return rentalCompanyService.createRentalCompany(rentalCompany);
    }
    @PostMapping(path = "/rentalcompanies/multi")
    public List<RentalCompany> createBooking(@Valid @RequestBody List<RentalCompany> rentalcompanies){
        return rentalCompanyService.addMultipleRentalCompany(rentalcompanies);
    }

    @PutMapping(path = "/rentalcompanies/{id}")
    public ResponseEntity<?> updateRentalCompany(@PathVariable int id,@Valid  @RequestBody RentalCompany rentalCompany) {
        RentalCompany updatedCompany = rentalCompanyService.updateRentalCompany(id, rentalCompany);
        if (updatedCompany != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedCompany);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rental Company with ID " + id + " not found");
        }
    }

    @DeleteMapping(path = "/rentalcompanies/{id}")
    public ResponseEntity<String> deleteRentalCompany(@PathVariable int id) {
        return rentalCompanyService.deleteRentalCompany(id)
                ? ResponseEntity.status(HttpStatus.OK).body("Rental Company deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental Company deletion failed");
    }

    @GetMapping("/rentalcompanies/paginated")
    public Page<RentalCompany> getPaginatedRentalCompanies(@RequestParam int page,@RequestParam int size){
        return rentalCompanyService.getPaginatedRentalCompanies(page,size);
    }

     @GetMapping("/rentalcompanies/search")
     public Page<RentalCompany> getPaginatedRentalCompaniesBylocation(@RequestParam String location,@RequestParam int page,@RequestParam int size){
         return rentalCompanyService.getPaginatedRentalCompaniesBylocation(location,page,size);
     }
    @GetMapping(path = "/rentalcompanies/find")
    public List<RentalCompany> getRentalCompaniesByLocation(@RequestParam String location){
        return rentalCompanyService.getRentalCompanyByLocation(location);
    }


}
