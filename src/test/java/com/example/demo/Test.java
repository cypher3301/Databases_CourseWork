package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.entity.Package;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.ValidationException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
public class Test {

    SessionFactory sessionFactory;

    public void setSessionFactory() {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public Session openSessionAndBeginTransaction() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public void closeSessionAndCommitTransaction(Session session) {
        session.getTransaction().commit();
        System.out.println("Stop");
        session.close();
    }

    void checkSessionFactory() {
        assert sessionFactory == null;
        setSessionFactory();
        assert sessionFactory != null;
        assert sessionFactory.isOpen();
    }

    Session checkSessionOpen() {
        Session session = openSessionAndBeginTransaction();
        assert session != null;
        assert session.isOpen();
        assert session.getTransaction().isActive();
        return session;
    }

    void checkSessionClose(Session session) {
        closeSessionAndCommitTransaction(session);
        assert !session.isOpen();
        assert !session.getTransaction().isActive();
    }

    byte[] hashPassword(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());
        return md.digest();
    }


    Client generateClient() {
        String firstname = "Anton";
        String middlename = "Ivanovich";
        String lastname = "Pavlov";
        PhoneNumber phoneNumber = null;
        try {
            phoneNumber = new PhoneNumber("+38096462749");
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        String region = "Nikolaivskaya";
        String city = "Nikolaev";
        String street = "Soborna";
        String postOfficeNumber = "3";

        Address address = new Address(
                region,
                city,
                street,
                postOfficeNumber);


        Client client = new Client();
        client.setFirstname(firstname);
        client.setMiddlename(middlename);
        client.setLastname(lastname);
        client.setPhoneNumber(phoneNumber);
        client.setAddress(address);

        return client;
    }

    Operator generateOperator() {
        String firstname = "Ivan";
        String middlename = "Antonov";
        String lastname = "Kabina";
        PhoneNumber phoneNumber = null;
        try {
            phoneNumber = new PhoneNumber("+38096232673");
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        String region = "Nikolaivskaya";
        String city = "Nikolaev";
        String street = "Mala morskaya";
        String houseNumber = "34/2";
        String apartmentNumber = "14";
        String zipcode = "23541";
        PlaceOfResidence placeOfResidence = new PlaceOfResidence(
                region,
                city,
                street,
                houseNumber,
                apartmentNumber,
                zipcode);

        long identificationCode = 1234567890L;
        long priceCardNumber = 1234_1234_1234_1234L;
        double price = 4_245.52;
        String login = "jd893njd";
        byte[] password = new byte[0];
        try {
            password = hashPassword("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Operator operator = new Operator();
        operator.setFirstname(firstname);
        operator.setMiddlename(middlename);
        operator.setLastname(lastname);
        operator.setPhoneNumber(phoneNumber);
        operator.setPlaceOfResidence(placeOfResidence);
        operator.setIdentificationCode(identificationCode);
        operator.setPriceCardNumber(priceCardNumber);
        operator.setPrice(price);
        operator.setLogin(login);
        operator.setPassword(password);

        return operator;
    }

    Courier generateCourier(){
        String firstname = "Ivan";
        String middlename = "Antonov";
        String lastname = "Kabina";
        PhoneNumber phoneNumber = null;
        try {
            phoneNumber = new PhoneNumber("+38096232673");
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        String region = "Nikolaivskaya";
        String city = "Nikolaev";
        String street = "Mala morskaya";
        String houseNumber = "34/2";
        String apartmentNumber = "14";
        String zipcode = "23541";
        PlaceOfResidence placeOfResidence = new PlaceOfResidence(
                region,
                city,
                street,
                houseNumber,
                apartmentNumber,
                zipcode);

        long identificationCode = 1234567891L;
        long priceCardNumber = 1234_1234_1234_1232L;
        double price = 4_245.52;

        String numberOfCar = "OL9456GN";

        Courier courier = new Courier();
        courier.setFirstname(firstname);
        courier.setMiddlename(middlename);
        courier.setLastname(lastname);
        courier.setPhoneNumber(phoneNumber);
        courier.setPlaceOfResidence(placeOfResidence);
        courier.setIdentificationCode(identificationCode);
        courier.setPriceCardNumber(priceCardNumber);
        courier.setPrice(price);
        courier.setNumberOfCar(numberOfCar);

        return courier;
    }

    Package generatePackages() {

        Status status = Status.WAIT_LOAD;

        double weight = 5.0d;
        double volume = 3.0d;
        double insurance = 450.0d;
        double quantity = 2.0d;

        String region = "Nikolaivskaya";
        String city = "Nikolaev";
        String street = "Soborna";
        String postOfficeNumber = "4";
        Address startAddress = new Address(
                region,
                city,
                street,
                postOfficeNumber);

        String region1 = "Nikolaivskaya";
        String city1 = "Nikolaev";
        String postOfficeNumber1 = "4";
        Address finishAddress = new Address(
                region1,
                city1,
                postOfficeNumber1);

        Timestamp loadDate = new Timestamp(System.nanoTime());

        Package pack = new Package();
        pack.setStatus(status.toString());
        pack.setWeight(weight);
        pack.setVolume(volume);
        pack.setInsurance(insurance);
        pack.setQuantity(quantity);
        pack.setLoadingAddress(startAddress);
        pack.setDeliveryAddress(finishAddress);
        pack.setLoadingDateAndTime(loadDate);


        return pack;
    }

    Invoice generateInvoice() {

        String region = "Nikolaivskaya";
        String city = "Nikolaev";
        String street = "Soborna";
        String postOfficeNumber = "4";
        Address startAddress = new Address(
                region,
                city,
                street,
                postOfficeNumber);

        String region1 = "Nikolaivskaya";
        String city1 = "Nikolaev";
        String postOfficeNumber1 = "4";
        Address finishAddress = new Address(
                region1,
                city1,
                postOfficeNumber1);

        Timestamp loadDate = new Timestamp(System.nanoTime());

        Invoice invoice = new Invoice();
        invoice.setLoadingAddress(startAddress);
        invoice.setLoadingDateAndTime(loadDate);
        invoice.setDeliveryAddress(finishAddress);
        return invoice;
    }
//
//    WorkShift generateWorkShift() {
//        WorkShift workShift = new WorkShift();
//        workShift.setStatus(WorkShiftStatus.STARTED);
//        workShift.setStartShift(new Timestamp(System.nanoTime()));
//        return workShift;
//    }


    @org.junit.jupiter.api.Test
    public void addClient() {
        Client client = generateClient();
        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(client);
        checkSessionClose(session);
    }


    @org.junit.jupiter.api.Test
    public void addOperator() {
        Operator operator = generateOperator();
        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(operator);
        checkSessionClose(session);

//        return operator;
    }


    @org.junit.jupiter.api.Test
    public void addCourier() {
        Courier courier = generateCourier();
        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(courier);
        checkSessionClose(session);
    }


    @org.junit.jupiter.api.Test
    public void addPackage() {
        Operator operator = generateOperator();
        WorkShift workShift = new WorkShift(operator);
        workShift.setStatus(WorkShiftStatus.STARTED);
        operator.getWorkShifts().add(workShift);
        Client client = generateClient();
        Package pack = generatePackages();
        Package pack2 = generatePackages();

        client.setPackages(pack,pack2);
        operator.setPackages(new ArrayList<>(Arrays.asList(pack, pack2)));
        pack.setOperator(operator);
        pack.setClient(client);
        pack2.setOperator(operator);
        pack2.setClient(client);



        checkSessionFactory();
        Session session = checkSessionOpen();

        session.save(operator);
        session.save(workShift);
        session.save(client);
        session.save(pack);
        session.save(pack2);
        checkSessionClose(session);
    }

    @org.junit.jupiter.api.Test
    public void invoiceStart() {
        Operator operator = generateOperator();

        WorkShift workShift = new WorkShift(operator);
        workShift.setStatus(WorkShiftStatus.STARTED);
        operator.getWorkShifts().add(workShift);

        Client client = generateClient();
        Package pack = generatePackages();
        Package pack2 = generatePackages();
        client.getPackages().add(pack);
        client.getPackages().add(pack2);
        operator.getPackages().add(pack);
        operator.getPackages().add(pack2);
        pack.setOperator(operator);
        pack.setClient(client);
        pack2.setOperator(operator);
        pack2.setClient(client);

        Courier courier = generateCourier();
        Invoice invoice = generateInvoice();



        invoice.setOperator(operator);
        invoice.setCourier(courier);
        invoice.getPackages().add(pack);
        invoice.getPackages().add(pack2);
        courier.getInvoice().add(invoice);

        WorkShift workShiftFinish = new WorkShift(operator, Arrays.asList(pack,pack2));
        workShiftFinish.setStatus(WorkShiftStatus.COMPLETED);
        operator.getWorkShifts().add(workShiftFinish);



        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(operator);
        session.save(workShift);
        session.save(client);
        session.save(pack);
        session.save(pack2);
        session.save(courier);
        session.save(invoice);
        session.save(workShiftFinish);
        checkSessionClose(session);

        assert true;
    }

    @org.junit.jupiter.api.Test
    public void invoiceFinish() {
        Operator operator = generateOperator();

        WorkShift workShift = new WorkShift(operator);
        workShift.setStatus(WorkShiftStatus.STARTED);
        operator.getWorkShifts().add(workShift);

        Client client = generateClient();
        Package pack = generatePackages();
        Package pack2 = generatePackages();
        client.getPackages().add(pack);
        client.getPackages().add(pack2);
        operator.getPackages().add(pack);
        operator.getPackages().add(pack2);
        pack.setOperator(operator);
        pack.setClient(client);
        pack2.setOperator(operator);
        pack2.setClient(client);

        Courier courier = generateCourier();
        Invoice invoice = generateInvoice();



        invoice.setOperator(operator);
        invoice.setCourier(courier);
        invoice.getPackages().add(pack);
        invoice.getPackages().add(pack2);
        courier.getInvoice().add(invoice);





        WorkShift workShiftFinish = new WorkShift(operator, Arrays.asList(pack,pack2));
        workShiftFinish.setStatus(WorkShiftStatus.COMPLETED);
        operator.getWorkShifts().add(workShiftFinish);


        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(operator);
        session.save(workShift);
        session.save(client);
        session.save(pack);
        session.save(pack2);
        session.save(courier);
        session.save(invoice);
        checkSessionClose(session);

        invoice.setDeliveryDateAndTime(new Timestamp(System.nanoTime()));
        Session session2 = checkSessionOpen();
        session2.save(invoice);
        session2.save(workShiftFinish);
        checkSessionClose(session2);

        assert true;
    }

    @org.junit.jupiter.api.Test
    void workShiftStart() {
        Client client = generateClient();
        Operator operator = generateOperator();
        Package pack = generatePackages();
        Package pack2 = generatePackages();
        client.getPackages().add(pack);
        client.getPackages().add(pack2);
        operator.getPackages().add(pack);
        operator.getPackages().add(pack2);
        pack.setOperator(operator);
        pack.setClient(client);
        pack2.setOperator(operator);
        pack2.setClient(client);

        WorkShift workShift = new WorkShift(operator, Arrays.asList(pack,pack2));
        workShift.setStatus(WorkShiftStatus.STARTED);
        operator.getWorkShifts().add(workShift);


        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(client);
        session.save(operator);
        session.save(pack);
        session.save(pack2);
        session.save(workShift);
        checkSessionClose(session);

        assert true;
    }

    @org.junit.jupiter.api.Test
    void workShiftComplete() {
        Client client = generateClient();
        Operator operator = generateOperator();
        Package pack = generatePackages();
        Package pack2 = generatePackages();
        client.getPackages().add(pack);
        client.getPackages().add(pack2);
        operator.getPackages().add(pack);
        operator.getPackages().add(pack2);
        pack.setOperator(operator);
        pack.setClient(client);
        pack2.setOperator(operator);
        pack2.setClient(client);

        WorkShift workShift = new WorkShift(operator, Arrays.asList(pack,pack2));
        workShift.setStatus(WorkShiftStatus.COMPLETED);
        operator.getWorkShifts().add(workShift);


        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(client);
        session.save(operator);
        session.save(pack);
        session.save(pack2);
        session.save(workShift);
        checkSessionClose(session);

        assert true;
    }
}
