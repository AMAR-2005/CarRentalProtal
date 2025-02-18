package com.project.application.repository;


import com.project.application.model.RentalCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalCompanyRepository extends JpaRepository<RentalCompany,Integer> {
    Page<RentalCompany> findBylocation(String location,Pageable pageable);

//    @Query("select r from RentalCompany r where r.location=:location")
    List<RentalCompany> findRentalCompaniesByLocation(@Param("location") String location);
}
