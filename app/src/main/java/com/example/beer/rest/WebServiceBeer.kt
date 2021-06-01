package com.example.beer.rest

import com.example.beer.rest.model.Beer
import retrofit2.Call
import retrofit2.http.GET

interface WebServiceBeer {

    @GET("/beers")
    fun getBeers() : Call<Beer>
}