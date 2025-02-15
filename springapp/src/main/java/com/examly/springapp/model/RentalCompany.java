package com.examly.springapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rental_id")
    @SequenceGenerator(name = "rental_id",allocationSize = 1)
    private int id;
    private String companyName;
    private String location;
    //private Vehicle vehicles;
    
    

}
