package com.spring.post.repository.kotlin

import com.spring.post.entity.Driver
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DriverRepository : JpaRepository<Driver, Long> {

//    fun findAllBy

//    @Query(
//        nativeQuery = true,
//        value = "select * from public.driver d " +
//                "where d.id in (select subW.driver_id from public.waybill subW where subW.id=:waybillId)"
//    )
//    fun findDriverByWaybillId(waybillId: Long): Driver;
//
//
//    @Query("select d from driver d where car.carNumber = :carNumber")
//    fun findByCar(carNumber: String): Driver;
//
//
//
//    @Query("SELECT d FROM driver d WHERE d.address.city = :city")
////    @Query(value = "select * from public.driver d where d.city = :city", nativeQuery = true)
//    fun findAllByCity(@Param("city") city: String): Collection<Driver>;
//
//    fun findByPhone(phone: String): Driver;
//
//    fun findByEmail(email: String): Driver;
//
//    fun findByIdentificationCode(identificationCode: String): Driver;
//
//    fun findByPriceCardNumber(priceCardNumber: String): Driver


}