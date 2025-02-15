package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.RentalCompany;
import com.examly.springapp.repository.RentalCompanyRepository;

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
        return rentalCompanyRepository.save(rentalCompany); 
    }
    
    public boolean deleteRentalCompany(int id) {
         if(rentalCompanyRepository.existsById(id)){
             rentalCompanyRepository.deleteById(id);
              return true;
        }
         return false;
    }
    public RentalCompany updateRentalCompany(int id, RentalCompany rentalCompany) {
        rentalCompany.setId(id);
        return rentalCompanyRepository.save(rentalCompany); 
    }

    public Page<RentalCompany> getPaginatedRentalCompanies(int page, int size) {
        Pageable pageable =PageRequest.of(page, size,Sort.by("CompanyName").ascending());
        return rentalCompanyRepository.findAll(pageable);
    }

    // public Page<RentalCompany> getPaginatedRentalCompaniesBylocation(String location, int page, int size) {
    //     Pageable pageable =PageRequest.of(page, size,Sort.by("CompanyName").ascending());
    //     return rentalCompanyRepository.findAll(pageable );
    // }


}
