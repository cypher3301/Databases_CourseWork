package com.example.demo.controller;

import com.example.demo.dto.ClientDto;
import com.example.demo.dto.OperatorDto;
import com.example.demo.dto.PackageDto;
import com.example.demo.entity.Client;
import com.example.demo.entity.Package;
import com.example.demo.service.Service;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/package")
@AllArgsConstructor
@Log
@CrossOrigin
public class PackageController {
    private final Service service;

    @PostMapping("/regPack")
    public PackageDto arrangeClientPackageByOperator(
            @RequestBody Data data
    ) {
        PackageDto packageDto = null;
        try {
            packageDto = service.registerPackage(data.sender,data.recipient,data.pack);
        } catch (ValidationException e) {
            System.out.println("/regPack service registerPackage");
            e.printStackTrace();
        }
        return packageDto;
    }

    public static class Data{
        public ClientDto sender;
        public ClientDto recipient;
        public PackageDto pack;
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

}
