package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Created by Marko 16 June 2021.
 */
class Fermentation {
    @SerializedName("temp")
    @Expose
    var temp: Temp__1? = null
}