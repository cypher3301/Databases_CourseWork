package com.example.demo.entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
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
}
