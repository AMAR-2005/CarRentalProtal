package com.project.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Location is required")
    private String location;

    @OneToMany(mappedBy = "rentalCompany",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("rentalCompany")
    private List<Vehicle> vehicle;
}
