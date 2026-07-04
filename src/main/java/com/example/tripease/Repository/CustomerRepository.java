package com.example.tripease.Repository;

import com.example.tripease.Entity.Customer;
import com.example.tripease.Enum.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByGender(Gender gender);

    List<Customer>findByGenderAndAge(Gender gender,int age);

    Optional<Customer> findByEmail(String email);

    List<Customer>findByName(String name);


    @Query("select c from Customer c where c.gender=:gender and c.age>:age")   // HQL Hibernate querry language
    List<Customer> getAllByGenderAndAgeGreatherThan(@Param("gender") Gender gender,
                                                    @Param("age")int age);

    // yaha pe enum to enum comparision hota hai

    //    @Query(value="select * from customer_info  where gender=:gender and age>:age",nativeQuery = true) // SQL querry
    //   List<Customer> getAllByGenderAndAgeGreatherThan(@Param("gender") String gender,
    //                                                   @Param("age")int age);

    // yaha pe string to string comparision hota hai
}
