package com.example.tripease.Transformer;

import com.example.tripease.DTO.Request.BookingRequest;
import com.example.tripease.DTO.Response.BookingResponse;
import com.example.tripease.Entity.Booking;
import com.example.tripease.Entity.Cab;
import com.example.tripease.Entity.Customer;
import com.example.tripease.Entity.Driver;
import com.example.tripease.Enum.TripStatus;

public class BookingTransformer {

    public static Booking bookingRequestToBooking(BookingRequest bookingRequest ,double perKmRate){
       return Booking.builder()
               .pickup(bookingRequest.getPickup())
               .destination(bookingRequest.getDestination())
               .tripDistanceInKm(bookingRequest.getTripDistanceInKm())
               .tripstatus(TripStatus.ONGOING)
               .billAmount(bookingRequest.getTripDistanceInKm()*perKmRate)
               .build();
    }

    public static BookingResponse bookingTOBookingResponse(Booking booking, Customer customer,
                                                           Cab cab, Driver driver) {
        return BookingResponse.builder()
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .tripstatus(booking.getTripstatus())
                .billAmount(booking.getBillAmount())
                .bokedAt(booking.getBokedAt())
                .lastUpadate(booking.getLastUpadate())
                .customer(CustomerTransformer.customerToCustomerResponse(customer))
                .cab(CabTransformer.cabToCabResponse(cab,driver))
                .build();
    }
}
