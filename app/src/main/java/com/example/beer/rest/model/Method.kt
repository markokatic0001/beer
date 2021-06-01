package com.example.beer.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Method {
    @SerializedName("mash_temp")
    @Expose
    var mashTemp: List<MashTemp>? = null

    @SerializedName("fermentation")
    @Expose
    var fermentation: Fermentation? = null

    @SerializedName("twist")
    @Expose
    var twist: Any? = null
}