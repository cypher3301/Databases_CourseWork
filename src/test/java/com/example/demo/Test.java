package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.entity.Package;
import com.example.demo.entity.embeddable.Address;
import com.example.demo.entity.embeddable.PhoneNumber;
import com.example.demo.entity.embeddable.PlaceOfResidence;
import com.example.demo.entity.status.PackageStatus;
import com.example.demo.entity.status.WorkShiftStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.ValidationException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;

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

    Station generateStation(short postOffice){
        Station station = new Station();
        Address address = new Address();
        address.setRegion("Nukolaivskaya oblast");
        address.setCity("Nukolaiv");
        address.setStreet("Bogoyavlenskiy");
        address.setPostOfficeNumber(postOffice);
        station.setAddress(address);
        return station;
    }

    WorkShift generateWorkShift() {
        WorkShift workShift = new WorkShift();
        workShift.setStatus(WorkShiftStatus.STARTED);
        workShift.setTime(new Timestamp(System.nanoTime()));
        return workShift;
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
        short postOfficeNumber = 3;

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

        PackageStatus status = PackageStatus.AWAITING_DISPATCH;

        double weight = 5.0d;
        double volume = 3.0d;
        double insurance = 450.0d;
        double quantity = 2.0d;

        String region = "Nikolaivskaya";
        String city = "Nikolaev";
        String street = "Soborna";
        short postOfficeNumber = 4;
        Address startAddress = new Address(
                region,
                city,
                street,
                postOfficeNumber);

        Timestamp loadDate = new Timestamp(System.nanoTime());

        Package pack = new Package();
        pack.setStatus(status);
        pack.setWeight(weight);
        pack.setVolume(volume);
        pack.setInsurance(insurance);
        pack.setQuantity(quantity);
        pack.setActualAddress(startAddress);
        pack.setDatetimeSendingPackage(loadDate);

        return pack;
    }


    @org.junit.jupiter.api.Test
    void ClientPackage() {
        Station station = generateStation((short) 3);
        Station station2 = generateStation((short) 4);
        WorkShift workShift = generateWorkShift();
        Operator operator = generateOperator();
        Client client = generateClient();
        Client client2 = generateClient();
        Package pack = generatePackages();
        Package pack2 = generatePackages();

        station.getOperators().add(operator);
        workShift.setOperator(operator);
        workShift.getPackages().add(pack);
        workShift.getPackages().add(pack2);
        operator.setStation(station);
        operator.getWorkShifts().add(workShift);
        pack.setOperator(operator);
        pack2.setOperator(operator);

        ClientsPackages clientsPackages1 = new ClientsPackages(client, client2, pack);
        ClientsPackages clientsPackages2 = new ClientsPackages(client, client2, pack2);

        Courier courier = generateCourier();
        Invoice invoice = new Invoice();
        invoice.setStartStation(station);
        invoice.setEndStation(station2);
        invoice.setCourier(courier);
        invoice.setOperator(operator);
        invoice.setPackages(Arrays.asList(pack,pack2));
        invoice.setLoadingDateAndTime(new Timestamp(System.nanoTime()));
        operator.getInvoices().add(invoice);
        courier.getInvoice().add(invoice);



        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(station);
        session.save(station2);
        session.save(client);
        session.save(client2);
        session.save(operator);
        session.save(workShift);
        session.save(pack);
        session.save(pack2);
        session.save(clientsPackages1);
        session.save(clientsPackages2);
        session.save(courier);
        session.save(invoice);
        checkSessionClose(session);
    }
}
