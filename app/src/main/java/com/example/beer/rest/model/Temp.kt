package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Temp {
    @SerializedName("value")
    @Expose
    var value: Int? = null

    @SerializedName("unit")
    @Expose
    var unit: String? = null
}