package com.spring.post.repository;

import com.spring.post.entity.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {

    @Query(nativeQuery = true, value = "select * from work_shift where datetime=?1 and operator_id=?2")
    List<WorkShift> existsWorkShiftByOperatorAndDatetimeAfter(Date date, long operatorId);

}
