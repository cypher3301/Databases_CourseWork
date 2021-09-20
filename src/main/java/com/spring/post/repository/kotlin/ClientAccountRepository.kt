package com.spring.post.repository.kotlin

import com.spring.post.entity.ClientAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientAccountRepository : JpaRepository<ClientAccount, Long> {

}