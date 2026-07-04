package com.example.tripease.Controller;

import com.example.tripease.DTO.Request.CabRequest;
import com.example.tripease.DTO.Response.CabResponse;
import com.example.tripease.DTO.Response.DriverResponse;
import com.example.tripease.Entity.Driver;
import com.example.tripease.Service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @PostMapping("/get/register/driver/{driverId}")
    public CabResponse registerCab(@RequestBody CabRequest cabRequest,
                                         @PathVariable("driverId") int driverId){
        return cabService.registerCab(cabRequest,driverId);
    }
}
