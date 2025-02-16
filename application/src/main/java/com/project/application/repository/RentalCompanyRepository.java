package com.project.application.repository;


import com.project.application.model.RentalCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalCompanyRepository extends JpaRepository<RentalCompany,Integer> {
    Page<RentalCompany> findBylocation(String location,Pageable pageable);
}
