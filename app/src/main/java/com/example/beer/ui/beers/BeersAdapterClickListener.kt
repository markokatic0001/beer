package com.example.beer.ui.beers

import com.example.beer.room.BeerDB

/*
 * Created by Marko 16 June 2021.
 */
interface BeersAdapterClickListener {

    fun onBeerClicked(beer: BeerDB)
    fun onBeerLongClicked(pos: Int)
}