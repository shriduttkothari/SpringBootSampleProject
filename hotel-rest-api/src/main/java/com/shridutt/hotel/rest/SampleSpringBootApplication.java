package com.shridutt.hotel.rest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.shridutt.hotel.rest.dao.HotelRepository;
import com.shridutt.hotel.rest.model.Hotel;

@SpringBootApplication(scanBasePackages = "com.shridutt")
@EnableAutoConfiguration
public class SampleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(HotelRepository hotelRepository) throws Exception {
		return (String[] args) -> {
			ArrayList<Hotel> hotelsFromCSV = readCSV();
			hotelsFromCSV.stream().forEach(hotel -> {
				hotelRepository.save(hotel);
			});
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
	
	ArrayList<Hotel> buildHotel(Iterable<CSVRecord> records) {
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
}
