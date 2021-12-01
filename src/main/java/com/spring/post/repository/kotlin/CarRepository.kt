package com.spring.post.repository.kotlin

import com.spring.post.entity.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car?, Long?>{

}
