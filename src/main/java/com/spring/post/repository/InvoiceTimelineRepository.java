package com.spring.post.repository;

import com.spring.post.entity.InvoiceTimeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceTimelineRepository extends JpaRepository<InvoiceTimeline, Long> {
    List<InvoiceTimeline> findAllByActualStation_Id(long actualStationId);
}
