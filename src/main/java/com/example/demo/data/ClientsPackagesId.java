package com.example.demo.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientsPackagesId{
    private long senderId;
    private long recipientId;
    private long packageId;

}
