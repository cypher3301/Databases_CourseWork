package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
}
