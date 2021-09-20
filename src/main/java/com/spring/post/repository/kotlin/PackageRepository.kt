package com.spring.post.repository.kotlin

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PackageRepository : JpaRepository<Package, Long> {

}