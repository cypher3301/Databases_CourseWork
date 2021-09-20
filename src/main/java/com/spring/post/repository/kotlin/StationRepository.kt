package com.spring.post.repository.kotlin

import com.spring.post.entity.Station
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StationRepository : JpaRepository<Station, Long> {

}