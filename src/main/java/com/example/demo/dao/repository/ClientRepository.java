package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientById(Long id);
}
