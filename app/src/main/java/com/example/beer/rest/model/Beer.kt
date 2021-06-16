package com.example.beer.rest.model

import androidx.room.PrimaryKey
import com.example.beer.room.BeerDB
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Created by Marko 16 June 2021.
 */
class Beer {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("tagline")
    @Expose
    var tagline: String? = null

    @SerializedName("first_brewed")
    @Expose
    var firstBrewed: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null

    @SerializedName("abv")
    @Expose
    var abv: Double? = null

    @SerializedName("ibu")
    @Expose
    var ibu: Double? = null

    @SerializedName("target_fg")
    @Expose
    var targetFg: Double? = null

    @SerializedName("target_og")
    @Expose
    var targetOg: Double? = null

    @SerializedName("ebc")
    @Expose
    var ebc: Double? = null

    @SerializedName("srm")
    @Expose
    var srm: Double? = null

    @SerializedName("ph")
    @Expose
    var ph: Double? = null

    @SerializedName("attenuation_level")
    @Expose
    var attenuationLevel: Double? = null

    @SerializedName("volume")
    @Expose
    var volume: Volume? = null

    @SerializedName("boil_volume")
    @Expose
    var boilVolume: BoilVolume? = null

    @SerializedName("method")
    @Expose
    var method: Method? = null

    @SerializedName("ingredients")
    @Expose
    var ingredients: Ingredients? = null

    @SerializedName("food_pairing")
    @Expose
    var foodPairing: List<String>? = null

    @SerializedName("brewers_tips")
    @Expose
    var brewersTips: String? = null

    @SerializedName("contributed_by")
    @Expose
    var contributedBy: String? = null

    fun getBeerDB() : BeerDB {
        return BeerDB(id = id?.toLong(), name = name, description = description, imageUrl = imageUrl, abv = abv, ibu = ibu, ebc = ebc)
    }
}