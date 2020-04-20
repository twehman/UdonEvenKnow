package com.techelevator.favorites;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcFavoritesDao implements FavoritesDao {

    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JdbcFavoritesDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<Favorites> retrieveListsByUserId(long userId) {
    	List<Favorites> allFavorites = new ArrayList<>();
    	
    	String SqlGetAllFavorites = "SELECT * FROM favorites WHERE user_id = ?";
    	
    	SqlRowSet favoritesList = jdbcTemplate.queryForRowSet(SqlGetAllFavorites, userId);
    	
    	while (favoritesList.next()) {
    		Favorites theFavorites = mapRowToFavorites(favoritesList);
    		allFavorites.add(theFavorites);
    	}
    	return allFavorites;
    }

    @Override
    public Favorites createNewFavorite(Favorites aFavorite) {
    	Favorites newFavorites = new Favorites();
    	
    	String SqlNewFavorite = "INSERT INTO favorites (user_id, restaurant_name, restaurant_location, " +
    							"hours, rating, cuisine, price_range, image_url)" +
    							"VALUES(?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
    	
    	jdbcTemplate.update(SqlNewFavorite, aFavorite.getUserId(), aFavorite.getRestName(), aFavorite.getRestLocation(), aFavorite.getHours(),
    						aFavorite.getRating(), aFavorite.getCuisine(), aFavorite.getPriceRange(), aFavorite.getImageUrl());
    	
    	return newFavorites;
    }
    
    private int getNextFavId() {
    	String sqlGetNextFavId = "select nextval ('favorites_id_seq')";
    	
    	SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet(sqlGetNextFavId);
    	
    	int id = 0;
    
    	if(nextIdResult.next()) {
			id = nextIdResult.getInt(1);
			return id;
		} else {
			throw new RuntimeException("Something went wrong while getting an id");
		} 
    }
    
    private Favorites mapRowToFavorites (SqlRowSet favoritesList) {
    	Favorites aFavorite = new Favorites();
    	aFavorite.setId(favoritesList.getLong("id"));
    	aFavorite.setUserId(favoritesList.getLong("user_id"));
    	aFavorite.setRestName(favoritesList.getString("restaurant_name"));
    	aFavorite.setRestLocation(favoritesList.getString("restaurant_location"));
    	aFavorite.setHours(favoritesList.getString("hours"));
    	aFavorite.setRating(favoritesList.getString("rating"));
    	aFavorite.setCuisine(favoritesList.getString("cuisine"));
    	aFavorite.setPriceRange(favoritesList.getString("price_range"));
    	aFavorite.setImageUrl(favoritesList.getString("image_url"));
    	
    	return aFavorite;
    }
}
