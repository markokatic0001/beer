package com.example.beer.koin

import com.example.beer.ui.BeerListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BeerListViewModel(get()) }
}