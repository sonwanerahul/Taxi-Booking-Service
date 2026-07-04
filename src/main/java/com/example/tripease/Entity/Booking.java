package com.example.tripease.Entity;

import com.example.tripease.Enum.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="booking_info")
@Builder

public class Booking {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int bookingId;
    String pickup;
    String destination;
    double tripDistanceInKm;
    TripStatus tripstatus;
    double billAmount;

    @CreationTimestamp
    Date bokedAt;

    @UpdateTimestamp
    Date lastUpadate;


}
