package com.project.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email in invalid format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @ColumnDefault("0")
    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must be a valid Indian number (10 digits, starting with 6-9)")
    private String phone;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Booking> bookings;

}
