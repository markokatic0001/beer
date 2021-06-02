package com.example.beer.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BeersDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(beers: BeerDB)

    @Query("SELECT * FROM beers")
    fun beerList(): LiveData<List<BeerDB>?>
}