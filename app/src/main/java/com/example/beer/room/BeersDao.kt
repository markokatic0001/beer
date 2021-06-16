package com.example.beer.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
 * Created by Marko 16 June 2021.
 */
@Dao
interface BeersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(beers: List<BeerDB>?)

    @Query("SELECT * FROM beers")
    fun beerList(): List<BeerDB>?

    @Query("SELECT * FROM beers WHERE favorite")
    fun favorites(): List<BeerDB>?

    @Query("SELECT * FROM beers ORDER BY abv DESC")
    fun beerListAbv(): List<BeerDB>?

    @Query("SELECT * FROM beers ORDER BY ibu DESC")
    fun beerListIbu(): List<BeerDB>?

    @Query("SELECT * FROM beers ORDER BY ebc DESC")
    fun beerListEbc(): List<BeerDB>?

    @Query("UPDATE beers SET favorite = :favorite WHERE id = :id")
    fun updateBeer(id: Long, favorite: Boolean?): Int
}