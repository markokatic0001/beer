package com.example.beer.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BeersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(beers: List<BeerDB>?)

    @Query("SELECT * FROM beers")
    fun beerList(): LiveData<List<BeerDB>?>
}