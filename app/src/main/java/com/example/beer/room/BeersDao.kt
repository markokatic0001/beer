package com.example.beer.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BeersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(beers: List<BeerDB>?)

    @Query("SELECT * FROM beers")
    fun beerList(): LiveData<List<BeerDB>?>

    @Query("SELECT * FROM beers ORDER BY abv ASC")
    fun getBeersByAbv(compRef: String?): List<BeerDB>?

    @Query("SELECT * FROM beers ORDER BY ibu ASC")
    fun getBeersByIbu(compRef: String?): List<BeerDB>?

    @Query("SELECT * FROM beers ORDER BY ebc ASC")
    fun getBeersByEbc(compRef: String?): List<BeerDB>?
}