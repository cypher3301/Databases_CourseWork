package com.example.demo.converter;

import com.example.demo.dto.ClientDto;
import com.example.demo.dto.ClientsPackagesDto;
import com.example.demo.dto.OperatorDto;
import com.example.demo.dto.PackageDto;
import com.example.demo.entity.*;
import com.example.demo.entity.Package;
import com.example.demo.entity.embeddable.Address;
import com.example.demo.entity.embeddable.PhoneNumber;
import com.example.demo.entity.status.PackageStatus;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Convert {
    public Client fromClientDtoToClient(ClientDto dto) throws ValidationException {
        Client client = new Client();
        client.setId(dto.getId());
        client.setFirstname(dto.getFirstname());
        client.setMiddlename(dto.getMiddlename());
        client.setLastname(dto.getLastname());
        client.setPhoneNumber(new PhoneNumber(dto.getPhone()));
        client.setAddress(new Address(dto.getRegion(), dto.getCity(), dto.getStreet(), dto.getPostOfficeNumber()));
        return client;
    }

    public ClientDto fromClientToClientDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .firstname(client.getFirstname())
                .middlename(client.getMiddlename())
                .lastname(client.getLastname())
                .region(client.getAddress().getRegion())
                .city(client.getAddress().getCity())
                .street(client.getAddress().getStreet())
                .postOfficeNumber(client.getAddress().getPostOfficeNumber())
                .phone(client.getPhoneNumber().getPhone())
                .build();
    }

    public Operator fromOperatorDtoToOperator(OperatorDto dto) throws ValidationException, NoSuchAlgorithmException {
        Operator operator = new Operator();
        operator.setId(dto.getId());
        operator.setFirstname(dto.getFirstname());
        operator.setMiddlename(dto.getMiddlename());
        operator.setLastname(dto.getLastname());
        operator.setPhoneNumber(new PhoneNumber(dto.getPhone()));
        operator.setStation(new Station(new Address(dto.getRegion(), dto.getCity(), dto.getStreet(), dto.getStationNumber())));
        operator.setIdentificationCode(dto.getIdentificationCode());
        operator.setPrice(dto.getPrice());
        operator.setPriceCardNumber(dto.getPriceCardNumber());
        operator.setLogin("admin");
        operator.setPassword(hashPassword("admin"));
        return operator;
    }

    byte[] hashPassword(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());
        return md.digest();
    }


    public OperatorDto fromOperatorToOperatorDto(Operator operator) {
        return OperatorDto.builder()
                .id(operator.getId())
                .firstname(operator.getFirstname())
                .middlename(operator.getMiddlename())
                .lastname(operator.getLastname())
                .stationNumber(operator.getStation().getAddress().getPostOfficeNumber())
                .build();
    }

    public Package fromPackageDtoToPackage(PackageDto dto) {
        Package pack = new Package();
        pack.setId(dto.getId());
        pack.setActualAddress(new Address(dto.getRegion(), dto.getCity(), dto.getStreet(), dto.getPostOfficeNumber()));
        pack.setInsurance(dto.getInsurance());
        pack.setQuantity(dto.getQuantity());
        pack.setVolume(dto.getVolume());
        pack.setWeight(dto.getWeight());
        pack.setStatus(PackageStatus.getCurrentStatus(dto.getStatus()));
        pack.setDatetimeSendingPackage(dto.getFromDatetime());
        pack.setDatetimeReceiptPackage(dto.getToDatetime());
        return pack;
    }

    public PackageDto fromPackageToPackageDto(Package pack) {
        return PackageDto.builder()
                .id(pack.getId())
                .region(pack.getActualAddress().getRegion())
                .city(pack.getActualAddress().getCity())
                .street(pack.getActualAddress().getStreet())
                .postOfficeNumber(pack.getActualAddress().getPostOfficeNumber())
                .insurance(pack.getInsurance())
                .quantity(pack.getQuantity())
                .status(pack.getStatus().name())
                .volume(pack.getVolume())
                .weight(pack.getWeight())
                .fromDatetime(pack.getDatetimeSendingPackage())
                .toDatetime(pack.getDatetimeReceiptPackage())
                .build();
    }

    public ClientsPackages fromClientPackagesDtoToClientPackages(ClientsPackagesDto dto){
        ClientsPackages clientPackages = new ClientsPackages();
        clientPackages.setSender(dto.getClientSender());
        clientPackages.setRecipient(dto.getClientRecipient());
        clientPackages.setPackages(dto.getPack());
        return clientPackages;
    }

    public ClientsPackagesDto fromClientPackagesToClientPackagesDto(ClientsPackages dto){
        return ClientsPackagesDto.builder()
                .clientSender(dto.getSender())
                .clientRecipient(dto.getRecipient())
                .pack(dto.getPackages())
                .build();
    }



    }
