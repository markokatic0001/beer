package com.example.beer.room

import androidx.room.Room
import com.example.beer.BeerApplication

/*
 * Created by Marko 16 June 2021.
 */
class AppDb private constructor() {
    var appDatabase: AppDatabase? = null
        get() {
            if (field == null) {
                field = Room.databaseBuilder(
                    BeerApplication.instance.applicationContext,
                    AppDatabase::class.java, "beer.db"
                )
                    .fallbackToDestructiveMigration().build()
                // TODO: 2.6.21. Add migrations later
            }
            return field
        }
        private set

    companion object {
        var instance: AppDb? = null
            get() {
                if (field == null) field = AppDb()
                return field
            }
            private set
    }

    init {
        appDatabase
    }
}