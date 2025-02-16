package com.project.application.model;

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
    private List<Integer> bookings;
    // @OneToMany(targetEntity = Booking.class,mappedBy = "user",fetch = FetchType.LAZY)

//
//    public User() {
//    }
//
//    public User(int id, String name, String email, long phone, List<Integer> bookings) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.phone = phone;
//        this.bookings = bookings;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public long getPhone() {
//        return phone;
//    }
//
//    public void setPhone(long phone) {
//        this.phone = phone;
//    }
//
//    public List<Integer> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(List<Integer> bookings) {
//        this.bookings = bookings;
//    }
}
