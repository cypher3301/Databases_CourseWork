package com.spring.post.repository.kotlin

import com.spring.post.entity.WorkShift
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkShiftRepository : JpaRepository<WorkShift, Long> {

}