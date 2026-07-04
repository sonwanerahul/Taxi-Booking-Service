package com.example.tripease.Repository;

import com.example.tripease.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    @Query(value="Select * from driver_info where cab_id= :cabId" , nativeQuery = true)
    Driver getDriverByCabId(@Param("cabId") int cabId);
}