package com.example.tripease.Service;

import com.example.tripease.DTO.Request.BookingRequest;
import com.example.tripease.DTO.Response.BookingResponse;
import com.example.tripease.Entity.Booking;
import com.example.tripease.Entity.Cab;
import com.example.tripease.Entity.Customer;
import com.example.tripease.Entity.Driver;
import com.example.tripease.Exception.CabUnavilableException;
import com.example.tripease.Exception.CustomerNotFoundException;
import com.example.tripease.Repository.BookingRepository;
import com.example.tripease.Repository.CabRepository;
import com.example.tripease.Repository.CustomerRepository;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class BookingService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        Customer customer= optionalCustomer.get();
        Cab availableCab=cabRepository.getAvailableRandomly();
        if(availableCab==null){
            throw new CabUnavilableException("Soory! No cabs avalible");
        }
        Booking booking= BookingTransformer.bookingRequestToBooking(bookingRequest, availableCab.getPerKmRate());
        Booking savedBooking=bookingRepository.save(booking);

        availableCab.setAvailable(false);
        customer.getBookings().add(savedBooking);

        Driver driver = driverRepository.getDriverByCabId(availableCab.getCabId());
        driver.getBookings().add(savedBooking);

        Customer savedCustomer = customerRepository.save(customer);
        Driver savedDriver = driverRepository.save(driver);

        sendEmail(savedCustomer);

        return BookingTransformer.bookingTOBookingResponse(savedBooking,savedCustomer,availableCab,savedDriver);
    }

    private void sendEmail(Customer customer){
        String text = "congrats !! " + customer.getName()+ "Your cab has been booked";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage .setFrom("rahulsonwane927@gmail.com");
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("Booking Confirmation");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

    }
}
