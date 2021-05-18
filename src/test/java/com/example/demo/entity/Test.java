package com.example.demo.entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;


//@SpringBootTest
public class Test {
    SessionFactory sessionFactory;

    public void setSessionFactory() {
        try {
            sessionFactory = new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory();
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

    @org.junit.jupiter.api.Test
    void Package() {
        Client client = new Client();
        Client client2 = new Client();
        Package pack = new Package();
        Package pack2 = new Package();
        ClientsPackages clientsPackages1 = new ClientsPackages(client, client2, pack);
        ClientsPackages clientsPackages2 = new ClientsPackages(client, client2, pack2);


        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(client);
        session.save(client2);
        session.save(pack);
        session.save(pack2);
        session.save(clientsPackages1);
        session.save(clientsPackages2);
        checkSessionClose(session);

    }

    @org.junit.jupiter.api.Test
    void ClientPackage() {
        Client client = new Client();
        Client client2 = new Client();
        Package pack = new Package();
        Package pack2 = new Package();
        ClientsPackages clientsPackages1 = new ClientsPackages(client, client2, pack);
        ClientsPackages clientsPackages2 = new ClientsPackages(client, client2, pack2);


        checkSessionFactory();
        Session session = checkSessionOpen();
        session.save(client);
        session.save(client2);
        session.save(pack);
        session.save(pack2);
        session.save(clientsPackages1);
        session.save(clientsPackages2);
        checkSessionClose(session);

    }
}
