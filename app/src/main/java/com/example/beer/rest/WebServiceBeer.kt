package com.example.beer.rest

import com.example.beer.rest.model.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by Marko 16 June 2021.
 */
interface WebServiceBeer {

    @GET("beers")
    fun getBeers(@Query("page") page: String, @Query("per_page") perPage: String) : Call<List<Beer>>
}