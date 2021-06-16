package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Created by Marko 16 June 2021.
 */
class Hop {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("amount")
    @Expose
    var amount: Amount__1? = null

    @SerializedName("add")
    @Expose
    var add: String? = null

    @SerializedName("attribute")
    @Expose
    var attribute: String? = null
}