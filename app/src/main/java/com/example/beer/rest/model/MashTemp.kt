package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MashTemp {
    @SerializedName("temp")
    @Expose
    var temp: Temp? = null

    @SerializedName("duration")
    @Expose
    var duration: Int? = null
}