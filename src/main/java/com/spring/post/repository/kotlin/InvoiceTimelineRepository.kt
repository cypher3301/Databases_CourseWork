package com.spring.post.repository.kotlin

import com.spring.post.entity.InvoiceTimeline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceTimelineRepository : JpaRepository<InvoiceTimeline, Long> {

}