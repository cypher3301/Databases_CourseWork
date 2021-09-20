package com.spring.post.repository.kotlin

import com.spring.post.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {

//    fun findByPhone(phone: String): Client
//
//    fun findAllBySurname(surname: String): Collection<Client>
//
//    //    @Query("select c,ca from client c inner join clientAccount ca on c.id=ca.client.id")
//    @Query("select * from client c inner join client_account ca on c.id=ca.client_id", nativeQuery = true)
//    fun findAllByEmail(email: String);
//
//    @Query(
//        nativeQuery = true, value =
//        "select * from invoice i inner join client c on c.id = i.sender_id where i.id in " +
//                "(select * from public.invoice where id=:invoiceId)"
//    )
//    fun findClientSenderByInvoiceId(invoiceId: Long): Client
//
//    @Query(
//        nativeQuery = true, value =
//        "select * from invoice i " +
//                "inner join client c on c.id = i.recipient_id " +
//                "where i.id in (select * from public.invoice where id=:invoiceId)"
//    )
//    fun findClientRecipientByInvoiceId(invoiceId: Long): Client

}