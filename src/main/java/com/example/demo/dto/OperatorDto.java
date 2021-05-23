package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperatorDto {
    private long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String phone;
    private long identificationCode;
    private String apartmentNumber;
    private String houseNumber;
    private String zipcode;
    private String region;
    private String city;
    private String street;
    private long priceCardNumber;
    private double price;
    private short stationNumber;
}
