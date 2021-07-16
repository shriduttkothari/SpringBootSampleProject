package com.shridutt.hotel.rest2.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shridutt.hotel.rest2.model.Motel;

@Repository
public interface MotelRepository extends JpaRepository<Motel, Long> {

	Page<Motel> findByCityId(Long cityId, Pageable pageable);

	Optional<Motel> findByIdAndCityId(Long id, Long cityId);
}
