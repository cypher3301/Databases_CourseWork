package com.spring.post.repository;

import com.spring.post.entity.Station;
import com.spring.post.entity.embeddable.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    //    Station findBy
    Station findByAddressAndNumber(Address address, short number);

}
