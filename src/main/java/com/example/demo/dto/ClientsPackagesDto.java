package com.example.demo.dto;

import com.example.demo.entity.Client;
import com.example.demo.entity.Package;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientsPackagesDto {
    private long id;
    private Package pack;
    private Client clientSender;
    private Client clientRecipient;

    public ClientsPackagesDto(Client clientSender, Client clientRecipient, Package pack) {
        this.pack = pack;
        this.clientSender = clientSender;
        this.clientRecipient = clientRecipient;
    }
}
