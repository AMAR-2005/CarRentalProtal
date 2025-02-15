package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.RentalCompany;
import com.examly.springapp.model.User;
import com.examly.springapp.service.RentalCompanyService;

@RestController
@RequestMapping(path = "/api")
public class RentalCompanyController {
    
    @Autowired
    RentalCompanyService rentalCompanyService;

    
    @GetMapping(path = "/rentalcompanies")
    public List<RentalCompany> getAllRentalCompanies() {
        return rentalCompanyService.getAllRentalCompanies();
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

    @PostMapping(path = "/rentalcompanies")
    public RentalCompany createRentalCompany(@RequestBody RentalCompany rentalCompany) {
        return rentalCompanyService.createRentalCompany(rentalCompany);
    }

    @PutMapping(path = "/rentalcompanies/{id}")
    public ResponseEntity<?> updateRentalCompany(@PathVariable int id, @RequestBody RentalCompany rentalCompany) {
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

    @GetMapping("/getRental")
    public Page<RentalCompany> getPaginatedRentalCompanies(@RequestParam int page,@RequestParam int size){
        return rentalCompanyService.getPaginatedRentalCompanies(page,size);
    }

    // @GetMapping("/rentalcompanies/search")
    // public Page<RentalCompany> getPaginatedRentalCompaniesBylocation(@RequestParam String location,@RequestParam int page,@RequestParam int size){
    //     return rentalCompanyService.getPaginatedRentalCompaniesBylocation(location,page,size);
    // }


}
