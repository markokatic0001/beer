package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Created by Marko 16 June 2021.
 */
class Malt {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("amount")
    @Expose
    var amount: Amount? = null
}