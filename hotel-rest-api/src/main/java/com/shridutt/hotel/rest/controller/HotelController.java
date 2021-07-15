package com.shridutt.hotel.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shridutt.hotel.rest.dao.HotelRepository;
import com.shridutt.hotel.rest.dao.HotelServiceDAO;
import com.shridutt.hotel.rest.model.ApiResponse;
import com.shridutt.hotel.rest.model.Hotel;
import com.shridutt.hotel.rest.model.SearchResults;

@RestController
public class HotelController {

	public static final int NOT_FOUND = 404;
	public static final String NOT_FOUND_MESSAGE = "Record(s) not found";

	@Autowired
	private HotelServiceDAO hotelServiceDAO;
	
	@Autowired
	private HotelRepository hotelRepository;

	@RequestMapping(value = "/search/term", method = RequestMethod.GET)
	public ResponseEntity<Object> getSearchTerm(
			@RequestParam(required = false, name = "term", defaultValue = "") String term) {
		List<String> searchTermList = hotelServiceDAO.searchTerm(term);
		if (searchTermList == null || searchTermList.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse(NOT_FOUND, NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(searchTermList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/hotels", method = RequestMethod.GET)
	public ResponseEntity<Object> getHotels(
			@RequestParam(required = false, name = "term", defaultValue = "") String term,
			@RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
			@RequestParam(required = false, name = "limit", defaultValue = "10") int limit,
			@RequestParam(required = false, name = "sort", defaultValue = "id") String sortcolumn,
			@RequestParam(required = false, name = "sorttype", defaultValue = "asc") String sorttype) {

		List<Hotel> searchResultList = hotelServiceDAO.searchHotels(term, offset, limit, sortcolumn, sorttype);
		if (searchResultList == null || searchResultList.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse(NOT_FOUND, NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
		}

		int count = hotelServiceDAO.getCount(term);
		SearchResults searchResults = new SearchResults();
		searchResults.setSearchResults(searchResultList);
		searchResults.setTotalCount(count);

		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/hotels/category", method = RequestMethod.GET)
	public ResponseEntity<Object> categorySearch(
			@RequestParam(required = false, name = "categorylist", defaultValue = "") String categorylist,
			@RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
			@RequestParam(required = false, name = "limit", defaultValue = "10") int limit,
			@RequestParam(required = false, name = "sort", defaultValue = "id") String sortcolumn,
			@RequestParam(required = false, name = "sorttype", defaultValue = "asc") String sorttype) {

		List<Hotel> searchResultList = hotelServiceDAO.categorySearch(categorylist, offset, limit, sortcolumn, sorttype);
		if (searchResultList == null || searchResultList.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse(NOT_FOUND, NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
		}

		int count = hotelServiceDAO.getCategoryCount(categorylist);
		SearchResults searchResults = new SearchResults();
		searchResults.setSearchResults(searchResultList);
		searchResults.setTotalCount(count);

		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/hotels/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getHotelById(@PathVariable("id") String id) {
		List<Hotel> searchResultList = hotelServiceDAO.searchHotelById(id);
		if (searchResultList == null || searchResultList.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse(NOT_FOUND, NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
		}
		SearchResults searchResults = new SearchResults();
		searchResults.setSearchResults(searchResultList);
		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	public ResponseEntity<Object> getHotel() {
		List<Hotel> searchResultList = (List<Hotel>) hotelRepository.findAll();
		if (searchResultList == null || searchResultList.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse(NOT_FOUND, NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
		}
		SearchResults searchResults = new SearchResults();
		searchResults.setSearchResults(searchResultList);
		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}
}
