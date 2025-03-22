package com.project.application.service;


import com.project.application.model.Booking;
import com.project.application.model.RentalCompany;
import com.project.application.model.Vehicle;
import com.project.application.repository.RentalCompanyRepository;
import org.springframework.beans.BeanUtils;
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
public class RentalCompanyService {

    @Autowired
    RentalCompanyRepository rentalCompanyRepository;

    public List<RentalCompany> getAllRentalCompanies() {
         return rentalCompanyRepository.findAll(); 
    }
    
    public Optional<RentalCompany> getRentalCompanyById(int id) {
         return rentalCompanyRepository.findById(id);
     }
     
    public RentalCompany createRentalCompany(RentalCompany rentalCompany) {
        List<Vehicle> vehicleList = rentalCompany.getVehicle();
        if(vehicleList!=null){
            for (Vehicle vehicle : vehicleList) {
                vehicle.setRentalCompany(rentalCompany);
            }
        }
        return rentalCompanyRepository.save(rentalCompany); 
    }
    
    public boolean deleteRentalCompany(int id) {
         if(rentalCompanyRepository.existsById(id)){
             rentalCompanyRepository.deleteById(id);
              return true;
        }
         return false;
    }
    public RentalCompany updateRentalCompany(int id, RentalCompany updatedRentalCompany) {
        RentalCompany existingRentalCompany = rentalCompanyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RentalCompany not found"));

        BeanUtils.copyProperties(updatedRentalCompany, existingRentalCompany, "id");
        if (updatedRentalCompany.getVehicle() != null) {
            updatedRentalCompany.getVehicle().forEach(vehicle -> vehicle.setRentalCompany(existingRentalCompany));
            existingRentalCompany.setVehicle(updatedRentalCompany.getVehicle());
        }
        return rentalCompanyRepository.save(existingRentalCompany);
    }

    public Page<RentalCompany> getPaginatedRentalCompanies(int page, int size) {
        Pageable pageable =PageRequest.of(page, size,Sort.by("CompanyName").ascending());
        return rentalCompanyRepository.findAll(pageable);
    }
    public List<RentalCompany> getSortedRentalCompanies() {
        return rentalCompanyRepository.findAll(Sort.by("companyName").ascending());
    }

     public Page<RentalCompany> getPaginatedRentalCompaniesBylocation(String location, int page, int size) {
         Pageable pageable =PageRequest.of(page, size,Sort.by("CompanyName").ascending());
         return rentalCompanyRepository.findBylocation(location,pageable);
     }

    public List<RentalCompany> getRentalCompanyByLocation(String location) {
        return rentalCompanyRepository.findRentalCompaniesByLocation(location);
    }
    public List<RentalCompany> getRentalCompaniesByName(String companyName) {
        return rentalCompanyRepository.findBycompanyName(companyName);
    }

    public List<RentalCompany> addMultipleRentalCompany(List<RentalCompany> rentalcompanies) {
        return  rentalCompanyRepository.saveAll(rentalcompanies);
    }
}
