package com.spring.post.generate;

import com.spring.post.entity.*;
import com.spring.post.entity.Package;
import com.spring.post.entity.status.InvoiceType;
import com.spring.post.entity.status.PackageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateInvoice extends Generator {

    public static List<Invoice> getListInvoices(List<Invoice> invoices) {
        if (invoices == null){
            invoices = new ArrayList<>();
        }
        for (int i = 1; i <= new Random().nextInt(255); i++) {
            invoices.add(getNewInvoice());
        }
        return invoices;
    }

    public static List<Invoice> getListInvoices(List<Invoice> invoices, Client sender, Client recipient, Operator operator, Station stationRecipient) {
        if (invoices == null){
            invoices = new ArrayList<>();
        }
        for (int i = 1; i <= new Random().nextInt(255); i++) {
            invoices.add(getNewInvoice(sender, recipient, operator, stationRecipient));
        }
        return invoices;
    }

    public static List<Invoice> getListInvoices(List<Invoice> invoices, Operator operator) {
        if (invoices == null){
            invoices = new ArrayList<>();
        }
        for (int i = 1; i <= new Random().nextInt(255); i++) {
            invoices.add(getNewInvoice(GenerateClient.generateNewClient(), GenerateClient.generateNewClient(), operator, GenerateStation.getNewStation()));
        }
        return invoices;
    }




    public static Invoice getNewInvoice() {
        GenerateInvoice generateInvoice = new GenerateInvoice();
        Invoice invoice = new Invoice();
        invoice.setId(generateInvoice.generateId());
        invoice.setDatetime(generateInvoice.generateDatetime());
        invoice.setDeliveryType(generateInvoiceType());
        invoice.setQuantity(new Random().nextInt(3) + 1);
        invoice.setType(generateSeveralPackageTypes());
        invoice.setClientRecipient(GenerateClient.generateNewClient());
        invoice.setClientSender(GenerateClient.generateNewClient());
        invoice.setOperator(GenerateOperator.getNewOperator());
        invoice.setStationRecipient(GenerateStation.getNewStation());
        if (invoice.getPackages() != null) {
            for (int i = 1; i <= invoice.getQuantity(); i++) {
                invoice.getPackages().add(getNewPackage(i, invoice));
            }
        }
        invoice.setPackages(getSeveralPackages(invoice.getQuantity(), invoice));
        return invoice;
    }

    public static Invoice getNewInvoice(Client sender, Client recipient, Operator operator, Station stationRecipient) {
        GenerateInvoice generateInvoice = new GenerateInvoice();
        Invoice invoice = new Invoice();
        invoice.setId(generateInvoice.generateId());
        invoice.setDatetime(generateInvoice.generateDatetime());
        invoice.setDeliveryType(generateInvoiceType());
        invoice.setQuantity(new Random().nextInt(3) + 1);
        invoice.setType(generateSeveralPackageTypes());
        invoice.setClientRecipient(recipient);
        invoice.setClientSender(sender);
        invoice.setOperator(operator);
        invoice.setStationRecipient(stationRecipient);
        if (invoice.getPackages() != null) {
            for (int i = 1; i <= invoice.getQuantity(); i++) {
                invoice.getPackages().add(getNewPackage(i, invoice));
            }
        }
        invoice.setPackages(getSeveralPackages(invoice.getQuantity(), invoice));
        return invoice;
    }

    private static PackageType[] generateSeveralPackageTypes() {
        PackageType[] packageType = new PackageType[new Random().nextInt(2) + 1];
        for (PackageType type : packageType) {
            type = generatePackageType();
        }
        return packageType;
    }

    private static PackageType generatePackageType() {
        int i = new Random().nextInt() % PackageType.getLength();
        switch (i) {
            case 0:
                return PackageType.PRODUCTS;
            case 1:
                return PackageType.CHEMISTRY;
            case 2:
                return PackageType.CLOTHES;
            case 3:
                return PackageType.ELECTRONICS;
            case 4:
                return PackageType.FURNITURE;
            case 5:
                return PackageType.LIQUID;
            case 6:
                return PackageType.MEDICATIONS;
            default:
                return PackageType.CLOTHES;
        }
    }

    private static InvoiceType generateInvoiceType() {
        if (new Random().nextInt(2) % 7 == 0) {
            return InvoiceType.RETURN;
        }
        return InvoiceType.DELIVERY;
    }

    private static List<Package> getSeveralPackages(int count, Invoice invoice) {
        List<Package> packages = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            packages.add(getNewPackage(i, invoice));
        }
        return packages;
    }

    private static Package getNewPackage(int number, Invoice invoice) {
        GenerateInvoice generateInvoice = new GenerateInvoice();
        GenerateCar generateCar = new GenerateCar();
        Package p = new Package();
        p.setId(generateInvoice.generateId());
        p.setInsurance(Double.parseDouble(generateInvoice.generateNumber(3)));
        p.setVolume(generateCar.generateVolume(0.1, 1));
        p.setWeight(generateCar.generateWeight(0.1, 20));
        p.setNumber(number);
        p.setInvoice(invoice);
        return p;
    }
}
