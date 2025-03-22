package com.project.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rental_id")
    @SequenceGenerator(name = "rental_id",allocationSize = 1)
    private int id;
    private String companyName;
    private String location;
    @OneToMany(mappedBy = "rentalCompany",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("rentalCompany")
    private List<Vehicle> vehicle;
}
