package com.example.demo.controller;

import com.example.demo.data.ClientsPackagesId;
import com.example.demo.dto.ClientDto;
import com.example.demo.dto.ClientsPackagesDto;
import com.example.demo.dto.OperatorDto;
import com.example.demo.dto.PackageDto;
import com.example.demo.service.Service;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/package")
@AllArgsConstructor
@Log
@CrossOrigin
public class PackageController {
    private final Service service;

    @Autowired
    public PackageController(Service service) {
        this.service = service;
    }

    private long recipient=0;
    private long sender=0;
    private long pack=0;


    @PostMapping("/createClientSender")
    public Long registerClientSender(@RequestBody ClientDto clientDto){
        log.info("Handling save client: " + clientDto);
        long id = service.saveClient(clientDto).getId();
        sender = id;
        return id;
    }

    @PostMapping("/createClientRecipient")
    public Long registerClientRecipient(@RequestBody ClientDto clientDto){
        log.info("Handling save client: " + clientDto);
        long id = service.saveClient(clientDto).getId();
        recipient=id;
        return id;
    }

    @PostMapping("/createPackage")
    public Long registerPackage(@RequestBody PackageDto packageDto){
        log.info("Handling save package: " + packageDto);
        long id = service.savePackage(packageDto).getId();
        pack=id;
        return id;
    }

    @PostMapping("/regPack")
    public ClientsPackagesDto registerPackage(@RequestBody ClientsPackagesId clientsPackages){
        while (sender == 0 || recipient == 0 || pack == 0){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                }
            },500);
        }
        clientsPackages.setSenderId(sender);
        clientsPackages.setRecipientId(recipient);
        clientsPackages.setPackageId(pack);
        ClientsPackagesDto clientsPackagesDto = service.saveClientsPackages(clientsPackages);
        log.info("Handling save clients-packages: "+clientsPackagesDto);

        resetIds();
        return clientsPackagesDto;
    }

    private void resetIds(){
        this.pack=0;
        this.sender=0;
        this.recipient=0;
    }

    @GetMapping("/findAllOperators")
    public List<OperatorDto> findAllOperators() {
        log.info("Handling find all operators request");
        return service.findAllOperators();
    }


    @GetMapping("/findAllPackages")
    public List<PackageDto> findAllPackages() {
        log.info("Handling find all packages request");
        return service.findAllPackages();
    }

    @GetMapping("/findAllClients")
    public List<ClientDto> findAllClients() {
        log.info("Handling find all packages request");
        return service.findAllClients();
    }

    @GetMapping("/findClientById")
    public ClientDto findClientById(@RequestParam Long id) {
        return service.findClientById(id);
    }

    @GetMapping("/closeInvoicePackage")
    public PackageDto closePackage(@RequestParam Long id){
        log.info("Handling close package: " + id);
        return service.closePackage(id);
    }

}
