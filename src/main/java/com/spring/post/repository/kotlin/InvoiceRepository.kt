package com.spring.post.repository.kotlin

import com.spring.post.entity.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : JpaRepository<Invoice, Long> {

}