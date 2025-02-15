package com.examly.springapp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "booking_id")
    @SequenceGenerator(name = "booking_id",allocationSize = 1)
    private int id;
    private int user;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalAmount;
    private boolean insuranceIncluded;

}
