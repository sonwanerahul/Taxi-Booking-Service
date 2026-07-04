package com.example.tripease.Entity;

import com.example.tripease.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="driver_info")
@Builder
public class Driver {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int driverId;
    private  String name;
    private  int age;

    @Column(unique = true,nullable = false) // unique Email and not null email
    private  String email;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name="driver_id")
    List<Booking> bookings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cab_id")
    Cab cab;
}
