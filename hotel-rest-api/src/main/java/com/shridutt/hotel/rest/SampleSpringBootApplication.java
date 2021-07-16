package com.shridutt.hotel.rest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shridutt.hotel.rest.dao.HotelRepository;
import com.shridutt.hotel.rest.model.Hotel;
import com.shridutt.hotel.rest2.dao.CityRepository;
import com.shridutt.hotel.rest2.dao.MotelRepository;
import com.shridutt.hotel.rest2.dao.RoomRepository;
import com.shridutt.hotel.rest2.model.City;
import com.shridutt.hotel.rest2.model.Motel;
import com.shridutt.hotel.rest2.model.Room;

@SpringBootApplication(scanBasePackages = "com.shridutt")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.shridutt" }) 
@EntityScan(basePackages = { "com.shridutt" })
public class SampleSpringBootApplication {

	@Value("${load.data}")
	private boolean loadData;

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringBootApplication.class, args);
	}

	@ConditionalOnProperty(name = "load.data", havingValue = "true")
	@Bean
	public CommandLineRunner run(HotelRepository hotelRepository) throws Exception {
		return (String[] args) -> {
			// if(loadData) {
			ArrayList<Hotel> hotelsFromCSV = readCSV();
			hotelsFromCSV.stream().forEach(hotel -> {
				hotelRepository.save(hotel);
			});
			// }
			hotelRepository.findAll().forEach(hotel -> System.out.println(hotel));
		};
	}

	private ArrayList<Hotel> readCSV() throws IOException {
		Reader in = new FileReader("src/main/resources/hotels.csv");
		CSVFormat.EXCEL.withHeader("id", "address", "categories", "city", "country", "latitude", "longitude", "name",
				"postalCode", "province", "reviews.rating", "reviews.text", "reviews.title", "reviews.username");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		return buildHotel(records);
	}

	private ArrayList<Hotel> buildHotel(Iterable<CSVRecord> records) {
		ArrayList<Hotel> hotelsFromCSV = new ArrayList<>();
		for (CSVRecord record : records) {
			Hotel hotel = new Hotel();
			hotel.setName(record.get("name"));
			hotel.setCity(record.get("city"));
			hotel.setCountry(record.get("country"));
			hotel.setAddress(record.get("address"));
			hotel.setCategories(record.get("categories"));
			hotel.setLatitude(record.get("latitude"));
			hotel.setLongitude(record.get("longitude"));
			hotel.setPostalcode(record.get("postalCode"));
			hotel.setProvince(record.get("province"));
			hotel.setRatings(record.get("reviews.rating"));
			hotel.setReviewText(record.get("reviews.text"));
			hotel.setReviewTitle(record.get("reviews.title"));
			hotel.setReviewUsername(record.get("reviews.username"));
			hotelsFromCSV.add(hotel);
		}
		return hotelsFromCSV;
	}
	
	@ConditionalOnProperty(name = "load.data", havingValue = "true")
	@Bean
	public CommandLineRunner run2(RoomRepository roomRepository, MotelRepository motelRepository, CityRepository cityRepository) throws Exception {
		return (String[] args) -> {
			City city = saveCity(cityRepository);
			Motel motel1 = saveMotel1(motelRepository, city);
			Motel motel2 = saveMotel2(motelRepository, city);
			saveRoom(roomRepository, motel1, "room_type_1", 100);
			saveRoom(roomRepository, motel1, "room_type_2", 200);
			saveRoom(roomRepository, motel1, "room_type_3", 300);
			saveRoom(roomRepository, motel2, "room_type_1", 100);
			saveRoom(roomRepository, motel2, "room_type_2", 200);
			saveRoom(roomRepository, motel2, "room_type_3", 300);
			
			cityRepository.findAll().forEach(cityFromDB -> System.out.println(cityFromDB));
			motelRepository.findAll().forEach(motel -> System.out.println(motel));
			roomRepository.findAll().forEach(room -> System.out.println(room));
			
			Pageable pageable = PageRequest.of(0, 10);
			Page<Room> allFoundRooms = roomRepository.findAll(pageable);
			LocalDate dateToCheck = LocalDate.of(2021, 07, 19);
			
			List<Room> roomsWIthAvailbility = allFoundRooms.map(room -> {
				HashMap<LocalDate, Boolean> availabilityHashMap = room.getAvailabilityHashMap();
				
				boolean isAvailable = availabilityHashMap.get(dateToCheck);
				if(isAvailable){
					return room;
				}
	           return null;
	        }).getContent();
			
			System.out.println("Room available" + roomsWIthAvailbility.size());
		};
	}

	private Room saveRoom(RoomRepository roomRepository, Motel motel, String roomType, int roomSize) {
		Room room = new Room();
		room.setType("room_1");
		room.setRoomSize(100);
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
