package com.example.demo.service;

import com.example.demo.dto.ClientDto;
import com.example.demo.dto.OperatorDto;
import com.example.demo.dto.PackageDto;
import com.example.demo.entity.Package;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface Service {
    ClientDto saveClient(ClientDto clientDto);
    PackageDto savePackage(PackageDto packageDto);
    OperatorDto saveOperator(OperatorDto operatorDto);

    List<PackageDto> findAllPackages();
    List<OperatorDto> findAllOperators();
    List<ClientDto> findAllClients();

    PackageDto registerPackage(ClientDto sender, ClientDto recipient, PackageDto pack) throws ValidationException;
}