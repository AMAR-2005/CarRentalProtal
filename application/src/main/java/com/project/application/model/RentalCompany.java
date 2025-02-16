package com.project.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
public class RentalCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rental_id")
    @SequenceGenerator(name = "rental_id",allocationSize = 1)
    private int id;
    private String companyName;
    private String location;
    //private Vehicle vehicles;


    public RentalCompany() {
    }

    public int getId() {
        return id;
    }

    public RentalCompany(int id, String companyName, String location) {
        this.id = id;
        this.companyName = companyName;
        this.location = location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
