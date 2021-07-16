package com.shridutt.hotel.rest2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shridutt.hotel.rest2.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
