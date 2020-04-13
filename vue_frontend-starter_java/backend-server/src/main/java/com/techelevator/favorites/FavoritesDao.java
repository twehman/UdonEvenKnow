package com.techelevator.favorites;

import java.util.List;

public interface FavoritesDao {

	public List <Favorites> retrieveListsByUserId(long userId);
	
	public Favorites createNewFavorite(Favorites aFavorite);

}
