package com.shridutt.hotel.rest2.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shridutt.hotel.rest2.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	Page<Room> findByMotelId(Long motelId, Pageable pageable);

	Optional<Room> findByIdAndMotelId(Long id, Long motelId);

}
