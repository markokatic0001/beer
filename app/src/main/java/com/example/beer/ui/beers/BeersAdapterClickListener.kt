package com.example.beer.ui.beers

import com.example.beer.room.BeerDB

interface BeersAdapterClickListener {

    fun onBeerClicked(beer: BeerDB)
}