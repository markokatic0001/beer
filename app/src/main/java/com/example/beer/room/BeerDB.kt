package com.example.beer.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beers")
class BeerDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "image_url")
    var imageUrl: String? = null,

    @ColumnInfo(name = "abv")
    var abv: Double? = null,

    @ColumnInfo(name = "ibu")
    var ibu: Double? = null,

    @ColumnInfo(name = "ebc")
    var ebc: Double? = null
)