package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Created by Marko 16 June 2021.
 */
class Amount {
    @SerializedName("value")
    @Expose
    var value: Double? = null

    @SerializedName("unit")
    @Expose
    var unit: String? = null
}