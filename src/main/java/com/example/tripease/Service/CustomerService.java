package com.example.tripease.Service;

import com.example.tripease.DTO.Request.CustomerRequest;
import com.example.tripease.DTO.Response.CustomerResponse;
import com.example.tripease.Entity.Customer;
import com.example.tripease.Enum.Gender;
import com.example.tripease.Exception.CustomerNotFoundException;
import com.example.tripease.Repository.CustomerRepository;
import com.example.tripease.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        // Request DTO -> Entity conversion
        Customer customer = CustomerTransformer.customerRequestToCustomer( customerRequest );

        // saved entity in DB
        Customer Savedcustomer= customerRepository.save(customer);

        // saved entity to ->Response DTO
        return CustomerTransformer.customerToCustomerResponse(customer);
    }

    public CustomerResponse getCustomer(int customerId) {
        customerRepository.findById(customerId);  // database me primary key ke base pe record search kargea
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        // Iska return type optional hote hai , optional ek class hai Jiske two result ho sake jo hamne pass kiya wo nahi to Empty.
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }
        // saved entity to ->Response DTO
        Customer savedCustomer = optionalCustomer.get();

        // saved entity to ->Response DTO
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }


    public List<CustomerResponse> getAllByGender(Gender gender) {
        List<Customer> customer= customerRepository.findByGender(gender);

        //entity -> Response DTO
        List<CustomerResponse> customerResponses = new ArrayList<>(); //empty list
        for(Customer customers:customer){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customers)); // covert to customer response using tranformer.

        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAge(Gender gender, int age) {
        List<Customer> customer= customerRepository.findByGenderAndAge(gender,age);

        //Entity ->Response DTO
        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customers:customer){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customers));
        }
        return customerResponses;

    }

    public CustomerResponse getByEmail(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Email ID");
        }
        Customer savedCustomer = optionalCustomer.get(); //saved entity to response DTO
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);

    }


    public List<CustomerResponse> getByName(String name) {
        List<Customer> customer= customerRepository.findByName(name);
        List<CustomerResponse> customerResponses=new ArrayList<>(); //entity to response DTO
        for(Customer customers:customer){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customers));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAgeGreatherThan(Gender gender, int age) {
        List<Customer> customer= customerRepository.getAllByGenderAndAgeGreatherThan(gender,age);

        //Entity ->Response DTO
        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customers:customer){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customers));
        }
        return customerResponses;
    }
}

