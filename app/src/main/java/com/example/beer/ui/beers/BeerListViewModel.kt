package com.example.beer.ui.beers

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beer.rest.BeerRepository
import com.example.beer.rest.OnApiResponse
import com.example.beer.rest.model.Beer
import com.example.beer.room.BeerDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeerListViewModel(val app: Application) : ViewModel() {

    val beersLiveData: MutableLiveData<List<BeerDB>> = MutableLiveData()

    fun getBeers() = viewModelScope.launch(Dispatchers.IO) {
            BeerRepository.getBeers(object: OnApiResponse{
                override fun onResult(success: Any?) {
                    val beersDB = mutableListOf<BeerDB>()
                    for(beer in (success as List<Beer>)){
                        beersDB.add(beer.getBeerDB())
                    }
                    beersLiveData.postValue(beersDB)
                }

                override fun onError(err: String?) {

                }
            })
    }
}