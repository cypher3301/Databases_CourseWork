package com.spring.post.repository.kotlin

import com.spring.post.entity.Operator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OperatorRepository : JpaRepository<Operator, Long> {

//    @Query(nativeQuery = true, value = "select * from public.operator o where login=:login and password=:password")
//    fun findOperatorByLoginAndPassword(login: String, password: Array<Byte>): Operator
//
//    @Query(nativeQuery = true, value = "select exists(select 1 from public.operator where login=:login)")
//    fun existsOperatorByLogin(login: String): Boolean

}