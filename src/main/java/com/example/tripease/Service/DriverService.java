package com.example.tripease.Service;

import com.example.tripease.DTO.Request.DriverRequest;
import com.example.tripease.DTO.Response.DriverResponse;
import com.example.tripease.Entity.Driver;
import com.example.tripease.Exception.DriverNotFoundException;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class DriverService {
    @Autowired
    DriverRepository driverRepository;

    public DriverResponse addDriver(DriverRequest driverRequest) {
        Driver driver= DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDriver=driverRepository.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }

    public DriverResponse getDriver(int driverId) {
        driverRepository.findById(driverId);
        Optional<Driver> OptionalDriver=driverRepository.findById(driverId);

        if(OptionalDriver.isEmpty()){
            throw new DriverNotFoundException("Invalid Driver Id");
        }
        Driver savedDriver=OptionalDriver.get();
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }
}
