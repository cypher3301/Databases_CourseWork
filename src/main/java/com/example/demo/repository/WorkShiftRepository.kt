package com.example.demo.repository

import com.example.demo.entity.WorkShift
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkShiftRepository : JpaRepository<WorkShift, Long> {

}