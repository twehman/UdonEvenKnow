package com.techelevator.favorites;

import java.util.List;

public interface FavoritesDao {

	public List <Favorites> retrieveListsByUserId();
	
	public Favorites createNewFavorite(Favorites aFavorite);

}
