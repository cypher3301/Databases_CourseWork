package com.spring.post.repository.kotlin

import com.spring.post.entity.Waybill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WaybillRepository : JpaRepository<Waybill, Long> {

}