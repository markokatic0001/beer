package com.example.beer.room

import android.os.Parcel
import android.os.Parcelable
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
    var ebc: Double? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean? = false
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeValue(abv)
        parcel.writeValue(ibu)
        parcel.writeValue(ebc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BeerDB> {
        override fun createFromParcel(parcel: Parcel): BeerDB {
            return BeerDB(parcel)
        }

        override fun newArray(size: Int): Array<BeerDB?> {
            return arrayOfNulls(size)
        }
    }
}