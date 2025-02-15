package com.examly.springapp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.RentalCompany;

@Repository
public interface RentalCompanyRepository extends JpaRepository<RentalCompany,Integer> {
    // Page<RentalCompany> findBylocation(String location);
}
