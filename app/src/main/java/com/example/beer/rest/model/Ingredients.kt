package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Created by Marko 16 June 2021.
 */
class Ingredients {
    @SerializedName("malt")
    @Expose
    var malt: List<Malt>? = null

    @SerializedName("hops")
    @Expose
    var hops: List<Hop>? = null

    @SerializedName("yeast")
    @Expose
    var yeast: String? = null
}