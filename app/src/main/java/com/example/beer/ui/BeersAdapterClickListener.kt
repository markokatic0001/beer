package com.example.beer.ui

import com.example.beer.room.BeerDB

interface BeersAdapterClickListener {

    fun onBeerClicked(participant: BeerDB)
}