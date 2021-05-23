package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto {
    private long id;
    private String region;
    private String city;
    private String street;
    private short postOfficeNumber;
    private double insurance;
    private double quantity;
    private String status;
    private double volume;
    private double weight;
    private Timestamp from_datetime;
    private Timestamp to_datetime;
}
