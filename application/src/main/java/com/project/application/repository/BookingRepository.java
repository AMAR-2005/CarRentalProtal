package com.project.application.repository;


import com.project.application.model.Booking;
import com.project.application.model.RentalCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findByinsuranceIncluded(boolean insuranceIncluded);

    @Query("select b from Booking b order by b.totalAmount desc limit 3")
    List<Booking> orderByAmount();

}
