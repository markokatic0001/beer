package com.example.beer.koin

import com.example.beer.ui.beers.BeerListViewModel
import com.example.beer.ui.favorites.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BeerListViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
}