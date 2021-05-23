package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String phone;
    private String region;
    private String city;
    private String street;
    private short postOfficeNumber;
}
