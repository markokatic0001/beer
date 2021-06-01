package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Amount {
    @SerializedName("value")
    @Expose
    var value: Double? = null

    @SerializedName("unit")
    @Expose
    var unit: String? = null
}