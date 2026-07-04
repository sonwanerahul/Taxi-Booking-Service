package com.example.tripease.Controller;
import com.example.tripease.DTO.Request.CustomerRequest;
import com.example.tripease.DTO.Response.CustomerResponse;
import com.example.tripease.Enum.Gender;
import com.example.tripease.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer") // common endpoint for customer controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping  ("/add") // endpoint
    public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.addCustomer(customerRequest);

    }

    @GetMapping("/get/customer-id/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") int customerId){
        return customerService.getCustomer(customerId);

    }

    @GetMapping("/get/gender/{gender}")  // Get All the people of a particular gender
    public List<CustomerResponse> getAllByGender(@PathVariable("gender") Gender gender){
        return customerService.getAllByGender(gender);

    }


    @GetMapping("/get")// Get All the people of a particular gender and age. Example all males of age 25.
    public List<CustomerResponse>getAllByGenderAndAge(@RequestParam ("gender")Gender gender,
                                                      @RequestParam ("age")int age){
        return customerService.getAllByGenderAndAge(gender,age);

    }

    @GetMapping("get/email/{email}") //get By particular mail
    public CustomerResponse getByEmail(@PathVariable ("email") String email){
        return customerService.getByEmail(email);

    }

    @GetMapping("/get-by-name")
    public List<CustomerResponse> getByName(@RequestParam("name") String name){
        return customerService.getByName(name);

    }
    //get all the people of a particular gender and whose age > input age
    @GetMapping("/get-by-age-greather-than")
    public List<CustomerResponse>getAllByGenderAndAgeGreatherThan(@RequestParam ("gender")Gender gender,
                                                      @RequestParam ("age")int age) {
        return customerService.getAllByGenderAndAgeGreatherThan(gender, age);


    }
}
