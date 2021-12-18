package com.spring.post.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class InvoiceDTO {
    private String type;
    private double weight;
    private double volume;
    private double insurance;
    private int count;
    private String description;

    public InvoiceDTO(String type, double weight, double volume, double insurance, int count, String description) {
        this.type = type;
        this.weight = weight;
        this.volume = volume;
        this.insurance = insurance;
        this.count = count;
        this.description = description;
    }
}
