package com.example.demo.entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void getPackages() {
    }

    @Test
    void setAddress() {
    }

    @Test
    void setPackages() {
    }


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

    @Test
    public Client addClient() {
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

        assert client.getFirstname().equals(firstname);
        assert client.getMiddlename().equals(middlename);
        assert client.getLastname().equals(lastname);
        assert client.getPhoneNumber().equals(phoneNumber);
        assert client.getAddress().getRegion().equals(region);
        assert client.getAddress().getCity().equals(city);
        assert client.getAddress().getStreet().equals(street);
        assert client.getAddress().getPostOfficeNumber().equals(postOfficeNumber);


        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(client);
        checkSessionClose(session);
        return client;
    }
}