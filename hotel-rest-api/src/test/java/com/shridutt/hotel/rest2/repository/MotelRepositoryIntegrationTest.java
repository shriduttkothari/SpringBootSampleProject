package com.shridutt.hotel.rest2.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shridutt.hotel.rest.SampleSpringBootApplication;
import com.shridutt.hotel.rest2.dao.CityRepository;
import com.shridutt.hotel.rest2.dao.MotelRepository;
import com.shridutt.hotel.rest2.dao.RoomRepository;
import com.shridutt.hotel.rest2.model.City;
import com.shridutt.hotel.rest2.model.Motel;
import com.shridutt.hotel.rest2.model.Room;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = {SampleSpringBootApplication.class})
@TestPropertySource(locations = { "classpath:context-test.properties" })
public class MotelRepositoryIntegrationTest {

	@Value("${load.data}")
	private boolean loadData;
	
	@Autowired
	private MotelRepository motelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Test
	public void test_room_availability_found() {
		buildData(roomRepository, motelRepository, cityRepository);

		Page<Room> allFoundRooms = roomRepository.findAll(PageRequest.of(0, 10));
		
		LocalDate dateToCheck = LocalDate.of(2021, 07, 19);
		
		List<Room> roomsWithAvailbility = allFoundRooms.get().map(room -> {
			Boolean isAvailable = room.getAvailabilityHashMap().get(dateToCheck);
			return (null != isAvailable && isAvailable) ? room: null;
        }).filter(Objects::nonNull)
		.collect(Collectors.toList());
		
		assertThat(roomsWithAvailbility.size()).isEqualTo(6);
	}
	
	@Test
	public void test_room_availability_not_found_due_to_no_bookings_defined() {
		buildData(roomRepository, motelRepository, cityRepository);

		Page<Room> allFoundRooms = roomRepository.findAll(PageRequest.of(0, 10));
		
		LocalDate dateToCheck = LocalDate.of(2021, 07, 30);
		
		List<Room> roomsWithAvailbility = allFoundRooms.get().map(room -> {
			Boolean isAvailable = room.getAvailabilityHashMap().get(dateToCheck);
			return (null != isAvailable && isAvailable) ? room: null;
        }).filter(Objects::nonNull)
		.collect(Collectors.toList());
		
		assertThat(roomsWithAvailbility.size()).isEqualTo(0);
	}

	@Test
	public void test_room_availability_not_found() {
		buildData(roomRepository, motelRepository, cityRepository);

		Page<Room> allFoundRooms = roomRepository.findAll(PageRequest.of(0, 10));
		
		LocalDate dateToCheck = LocalDate.of(2021, 07, 16);
		
		List<Room> roomsWithAvailbility = allFoundRooms.get().map(room -> {
			Boolean isAvailable = room.getAvailabilityHashMap().get(dateToCheck);
			return (null != isAvailable && isAvailable) ? room: null;
        }).filter(Objects::nonNull)
		.collect(Collectors.toList());
		
		assertThat(roomsWithAvailbility.size()).isEqualTo(0);
	}
	
	@Test
	public void test_findByPricePerNight_should_return_zero() {
		buildData(roomRepository, motelRepository, cityRepository);
		
		BigDecimal pricePerNight = new BigDecimal(999.00);
		Page<Room> actual = roomRepository.findByPricePerNight(pricePerNight, PageRequest.of(0, 10));
		
		assertThat(actual.getContent().size()).isEqualTo(0);
	}
	
	@Test
	public void test_findByPricePerNight_should_return_2() {
		buildData(roomRepository, motelRepository, cityRepository);
		
		BigDecimal pricePerNight = new BigDecimal(2000.00);
		Page<Room> actual = roomRepository.findByPricePerNight(pricePerNight, PageRequest.of(0, 10));
		
		assertThat(actual.getContent().size()).isEqualTo(2);
	}
	
	
	private void buildData(RoomRepository roomRepository, MotelRepository motelRepository, CityRepository cityRepository) {
		City city = saveCity(cityRepository);
		Motel motel1 = saveMotel1(motelRepository, city);
		Motel motel2 = saveMotel2(motelRepository, city);
		saveRoom(roomRepository, motel1, "room_type_1", 100, new BigDecimal(1000.00));
		saveRoom(roomRepository, motel1, "room_type_2", 200, new BigDecimal(2000.00));
		saveRoom(roomRepository, motel1, "room_type_3", 300, new BigDecimal(3000.00));
		
		saveRoom(roomRepository, motel2, "room_type_1", 100, new BigDecimal(4000.00));
		saveRoom(roomRepository, motel2, "room_type_2", 200, new BigDecimal(5000.00));
		saveRoom(roomRepository, motel2, "room_type_3", 300, new BigDecimal(6000.00));
	}
	
	private Room saveRoom(RoomRepository roomRepository, Motel motel, String roomType, int roomSize, BigDecimal pricePerNight) {
		Room room = new Room();
		room.setType("room_1");
		room.setRoomSize(100);
		room.setPricePerNight(pricePerNight);
		HashMap<LocalDate, Boolean> availabilityHashMap = new HashMap<>();
		
		availabilityHashMap.put(LocalDate.of(2021, 07, 16), false);
		availabilityHashMap.put(LocalDate.of(2021, 07, 17), false);
		availabilityHashMap.put(LocalDate.of(2021, 07, 18), false);
		availabilityHashMap.put(LocalDate.of(2021, 07, 19), true);
		availabilityHashMap.put(LocalDate.of(2021, 07, 20), true);
		room.setAvailabilityHashMap(availabilityHashMap);
		room.setMotel(motel);
		roomRepository.save(room);
				
		// Store Room
		return roomRepository.save(room);
	}
	
	private Motel saveMotel1(MotelRepository motelRepository, City city) {
		Motel motel1 = new Motel();
		motel1.setName("Grand Marriot");
		motel1.setCity(city);
		motelRepository.save(motel1);
				
		// Store Motel
		return motelRepository.save(motel1);
	}
	
	private Motel saveMotel2(MotelRepository motelRepository, City city) {
		Motel motel2 = new Motel();
		motel2.setName("Sayaji");
		motel2.setCity(city);
		
		// Store Motel
		return motelRepository.save(motel2);
	}

	private City saveCity(CityRepository cityRepository) {
		City city1 = new City();
		city1.setName("Indore");
		// Store city
		return cityRepository.save(city1);
	}
}
