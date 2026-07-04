package com.example.tripease.Entity;

import com.example.tripease.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor //Default Constractor
@AllArgsConstructor //Parameterised Constractor
@Getter //getter
@Setter //setter
@Entity // To tells JPA this is entity class to database
@Table (name="customer_info") // Change table name in DB
@Builder

public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Automatic gives PK.
    private Integer customerId;
    @Column (name="first_name") // Change column name in DB PK.
    private String name;
    private  int age;

    @Column(unique = true,nullable = false) // unique Email and not null email
    private  String email;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @OneToMany (cascade = CascadeType.ALL)    // one-> customer and Many->booking  relation
    @JoinColumn (name="customer_id")         // create forgian key in booking table and name is customer id
    List<Booking> bookings=new ArrayList<>();
}
