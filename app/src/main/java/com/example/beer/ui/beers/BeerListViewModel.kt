package com.example.beer.ui.beers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beer.rest.BeerRepository
import com.example.beer.rest.OnApiResponse
import com.example.beer.rest.model.Beer
import com.example.beer.room.AppDb
import com.example.beer.room.BeerDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeerListViewModel(val app: Application) : ViewModel() {

    fun getBeers() = viewModelScope.launch(Dispatchers.IO) {
            BeerRepository.getBeers(object: OnApiResponse{
                override fun onResult(success: Any?) {
                    val beersDB = mutableListOf<BeerDB>()
                    for(beer in (success as List<Beer>)){
                        val beerDB = beer.getBeerDB()
                        beersDB.add(beerDB)
                    }
                    Thread {
                        AppDb.instance?.appDatabase?.beersDao()?.update(beersDB)
                    }.start()
                }

                override fun onError(err: String?) {

                }
            })
    }

    fun getBeersDB(): LiveData<List<BeerDB>?>? {
       return AppDb.instance?.appDatabase?.beersDao()?.beerList()
    }
}