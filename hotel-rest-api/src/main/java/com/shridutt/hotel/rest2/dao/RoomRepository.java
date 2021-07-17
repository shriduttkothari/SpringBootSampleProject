package com.shridutt.hotel.rest2.dao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shridutt.hotel.rest2.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	Page<Room> findByMotelId(Long motelId, Pageable pageable);

	Optional<Room> findByIdAndMotelId(Long id, Long motelId);
	
	/**
     * Finds Room by using the pricePerNight as a search criteria to find rooms with less than given pricePerNight.
     * @param pricePerNight
     * @return  A paged list of rooms whose pricePerNight is less than or equals to given pricePerNight.
     *          If no persons is found, this method returns null.
     */
	@Query(value = "SELECT r FROM Room r WHERE r.pricePerNight <= ?1",
	        countQuery = "SELECT count(*) FROM Room")
	Page<Room> findByPricePerNight(BigDecimal pricePerNight, Pageable pageable);
	

}
