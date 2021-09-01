package com.example.demo.service;

import com.example.demo.data.ClientsPackagesId;
import com.example.demo.dto.ClientDto;
import com.example.demo.dto.ClientsPackagesDto;
import com.example.demo.dto.OperatorDto;
import com.example.demo.dto.PackageDto;

import java.util.List;

public interface Service {
    ClientDto saveClient(ClientDto clientDto);
    PackageDto savePackage(PackageDto packageDto);
    OperatorDto saveOperator(OperatorDto operatorDto);
    ClientsPackagesDto saveClientsPackages(ClientsPackagesId clientsPackagesId);

    ClientDto findClientById(Long id);



    List<PackageDto> findAllPackages();
    List<OperatorDto> findAllOperators();
    List<ClientDto> findAllClients();

    PackageDto closePackage(Long id);
}
