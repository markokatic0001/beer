package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Created by Marko 16 June 2021.
 */
class BoilVolume {
    @SerializedName("value")
    @Expose
    var value: Int? = null

    @SerializedName("unit")
    @Expose
    var unit: String? = null
}