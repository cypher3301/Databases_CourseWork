package com.spring.post.repository;

import com.spring.post.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {

    Operator findOperatorByLogin(String login);

    @Query(nativeQuery = true, value = "select exists(select 1 from public.operator where login=:login)")
    Boolean existsOperatorByLogin(String login);

    @Query(nativeQuery = true, value = "select * from operator")
    List<Operator> findAll();

}
