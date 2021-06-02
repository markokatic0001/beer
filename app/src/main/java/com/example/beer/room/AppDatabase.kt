package com.example.beer.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [BeerDB::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun fishAlbumDao(): BeersDao?
}