package com.shridutt.hotel.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shridutt.hotel.rest.model.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, String> {

}
