package com.project.application.repository;


import com.project.application.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer>{
    List<Vehicle> findBymodel(String model);
    @Modifying
    @Transactional
    @Query("DELETE FROM Vehicle v WHERE v.model = :model")
    int deleteByModel(@Param("model") String model);
}
