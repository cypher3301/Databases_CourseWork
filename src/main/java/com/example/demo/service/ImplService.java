package com.example.demo.service;

import com.example.demo.converter.Convert;
import com.example.demo.dto.ClientDto;
import com.example.demo.dto.OperatorDto;
import com.example.demo.dto.PackageDto;
import com.example.demo.entity.Client;
import com.example.demo.entity.ClientsPackages;
import com.example.demo.entity.Operator;
import com.example.demo.entity.Package;
import com.example.demo.repository.ClientPackagesRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OperatorRepository;
import com.example.demo.repository.PackageRepository;
import lombok.AllArgsConstructor;

import javax.xml.bind.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ImplService implements Service {

    private final ClientRepository   clientRepository;
    private final PackageRepository  packageRepository;
    private final OperatorRepository operatorRepository;
    private final ClientPackagesRepository clientPackagesRepository;
    private final Convert            convert;




    ////////////////save objects
    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        Client client = null;
        //validation
        try {
            client = clientRepository.save(convert.fromClientDtoToClient(clientDto));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        assert client != null;
        return convert.fromClientToClientDto(client);
    }

    @Override
    public PackageDto savePackage(PackageDto packageDto) {
        Package pack = packageRepository.save(convert.fromPackageDtoToPackage(packageDto));
        //validation
        return convert.fromPackageToPackageDto(pack);
    }

    @Override
    public OperatorDto saveOperator(OperatorDto operatorDto) {
        Operator operator = null;
        //validation
        try {
            operator = operatorRepository.save(convert.fromOperatorDtoToOperator(operatorDto));
        } catch (ValidationException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert operator != null;
        return convert.fromOperatorToOperatorDto(operator);
    }




    ///////////////delete objects



    //////////////////find all
    @Override
    public List<PackageDto> findAllPackages() {
        return packageRepository
                .findAll()
                .stream()
                .map(convert::fromPackageToPackageDto).collect(Collectors.toList());
    }

    @Override
    public List<OperatorDto> findAllOperators() {
        return operatorRepository
                .findAll()
                .stream()
                .map(convert::fromOperatorToOperatorDto).collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> findAllClients() {
        return clientRepository
                .findAll()
                .stream()
                .map(convert::fromClientToClientDto).collect(Collectors.toList());
    }

    ////////////////find any


    ///////////////other

    @Override
    public PackageDto registerPackage(ClientDto sender, ClientDto recipient, PackageDto pack) throws ValidationException {
        ClientsPackages clientsPackages = new ClientsPackages(
                convert.fromClientDtoToClient(sender),
                convert.fromClientDtoToClient(recipient),
                convert.fromPackageDtoToPackage(pack)
        );
        clientPackagesRepository.save(clientsPackages);
        return pack;
    }
}
