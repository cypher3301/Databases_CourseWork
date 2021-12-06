package com.spring.post.repository;

import com.spring.post.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(nativeQuery = true, value = "select * from invoice inner join invoice_timeline it on invoice.id = it.invoice_id where actual_station_id=? group by invoice.id")
    List<Invoice> findCurrent(long stationId);


}
