package com.example.beer.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BeersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(beers: List<BeerDB>?)

    @Query("SELECT * FROM beers")
    fun beerList(): List<BeerDB>?

    @Query("SELECT * FROM beers ORDER BY abv DESC")
    fun beerListAbv(): List<BeerDB>?

    @Query("SELECT * FROM beers ORDER BY ibu DESC")
    fun beerListIbu(): List<BeerDB>?

    @Query("SELECT * FROM beers ORDER BY ebc DESC")
    fun beerListEbc(): List<BeerDB>?
}