package com.example.tripease.DTO.Response;

import com.example.tripease.Enum.Gender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DriverResponse {
        private int driverId;
        private String name;
        private  int age;
        private  String email;

}
