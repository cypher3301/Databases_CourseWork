package com.example.demo.service;

import com.example.demo.converter.Convert;
import com.example.demo.data.ClientsPackagesId;
import com.example.demo.dto.ClientDto;
import com.example.demo.dto.ClientsPackagesDto;
import com.example.demo.dto.OperatorDto;
import com.example.demo.dto.PackageDto;
import com.example.demo.entity.Client;
import com.example.demo.entity.ClientsPackages;
import com.example.demo.entity.Operator;
import com.example.demo.entity.Package;
import com.example.demo.entity.status.PackageStatus;
import com.example.demo.repository.ClientPackagesRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OperatorRepository;
import com.example.demo.repository.PackageRepository;
import lombok.AllArgsConstructor;

import javax.xml.bind.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
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
        packageDto.setStatus(PackageStatus.AWAITING_DISPATCH.name());
        packageDto.setFromDatetime(new Timestamp(System.nanoTime()));
        Package pack =convert.fromPackageDtoToPackage(packageDto);
        Optional<Operator> optionalOperator = operatorRepository.findById(packageDto.getOperatorId());
        assert optionalOperator.isPresent();
//        pack.setOperator(optionalOperator.get());
        Package save = packageRepository.save(pack);
        //validation
        return convert.fromPackageToPackageDto(save);
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

    @Override
    public ClientsPackagesDto saveClientsPackages(ClientsPackagesId clientsPackagesId) {
        ClientDto sender = convert.fromClientToClientDto(clientRepository.findById(clientsPackagesId.getSenderId()).get());
        ClientDto recipient =convert.fromClientToClientDto(clientRepository.findById(clientsPackagesId.getRecipientId()).get());
        PackageDto pack = convert.fromPackageToPackageDto(packageRepository.findById(clientsPackagesId.getPackageId()).get());
        ClientsPackages clientsPackages = null;
        try {
            clientsPackages = new ClientsPackages(
                    convert.fromClientDtoToClient(sender),
                    convert.fromClientDtoToClient(recipient),
                    convert.fromPackageDtoToPackage(pack));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        assert clientsPackages != null;
        ClientsPackages save =
                clientPackagesRepository
                        .save(
                                clientsPackages
                        );
        return convert.fromClientPackagesToClientPackagesDto(save);
    }






    ///////////////delete objects



    //////////////////find


    @Override
    public ClientDto findClientById(Long id) {
        if (id!=null){
            Client client = clientRepository.findClientById(id);
            if (client!=null){
                return convert.fromClientToClientDto(client);
            }
        }
        return null;
    }

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




    ///////////////other

    @Override
    public PackageDto closePackage(Long id) {
        Optional<Package> byId = packageRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDatetimeReceiptPackage(new Timestamp(System.nanoTime()));
            Package save = packageRepository.save(byId.get());
            return convert.fromPackageToPackageDto(save);
        }
        return null;
    }

}
