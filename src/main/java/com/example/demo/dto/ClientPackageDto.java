package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientPackageDto {
    private long id;
    private long packageId;
    private long clientSenderId;
    private long clientRecipientId;
}
