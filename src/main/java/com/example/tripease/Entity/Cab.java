package com.example.tripease.Entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cab_info")
@Builder
public class Cab {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int cabId;
    private String cabNumber;
    private String cabModel;
    private double perKmRate;
    private boolean available;
}
