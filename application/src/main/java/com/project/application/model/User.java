package com.project.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id")
    @SequenceGenerator(name = "user_id",allocationSize = 1)
    private int id;
    private String name;
    private String email;
    @ColumnDefault("0")
    private long phone=0;
    @ElementCollection
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    @JsonBackReference
    private List<Integer> bookings;

}
