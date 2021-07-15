package com.shridutt.hotel.rest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shridutt.hotel.rest.dao.HotelRepository;
import com.shridutt.hotel.rest.model.Hotel;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class HotelRepositoryIntegrationTest {

	@Autowired
	private HotelRepository hotelRepository;

	@Test
	public void whenCalledSave_thenCorrectNumberOfUsers() {
		Hotel hotel = buildHotel();
		hotelRepository.save(hotel);
		
		List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();

		assertThat(hotels.size()).isEqualTo(1);

		assertThat(hotels.get(0).getName()).isEqualTo("Test HotelName");
		assertThat(hotels.get(0).getCity()).isEqualTo("Test City");
		assertThat(hotels.get(0).getCountry()).isEqualTo("Test Country");
	}

	private Hotel buildHotel() {
		Hotel hotel = new Hotel();
		hotel.setName("Test HotelName");
		hotel.setCity("Test City");
		hotel.setCountry("Test Country");

		return hotel;
	}
}