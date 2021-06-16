package com.example.beer.room

import androidx.room.Database
import androidx.room.RoomDatabase

/*
 * Created by Marko 16 June 2021.
 */
@Database(version = 2, exportSchema = false, entities = [BeerDB::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun beersDao(): BeersDao?
}