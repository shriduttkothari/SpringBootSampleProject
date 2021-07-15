package com.shridutt.hotel.rest.dao;

import java.util.List;

import com.shridutt.hotel.rest.model.Category;
import com.shridutt.hotel.rest.model.Hotel;

public interface HotelServiceDAO {

	public Category getCategories();

	public List<String> searchTerm(String term);

	public List<Hotel> searchHotels(String term, int offset, int limit, String sortcolumn, String sorttype);

	public int getCount(String term);

	public List<Hotel> categorySearch(String categorylist, int offset, int limit, String sortcolumn, String sorttype);

	int getCategoryCount(String categorylist);

	public List<Hotel> searchHotelById(String id);

	public void creteBooking(String rooms, String id, String date, String user_id);

}
