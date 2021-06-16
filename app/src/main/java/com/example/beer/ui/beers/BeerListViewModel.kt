package com.example.beer.ui.beers

import android.app.Application
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beer.rest.BeerRepository
import com.example.beer.rest.OnApiResponse
import com.example.beer.rest.model.Beer
import com.example.beer.room.AppDb
import com.example.beer.room.BeerDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
 * Created by Marko 16 June 2021.
 */
class BeerListViewModel(val app: Application) : ViewModel() {

    val beerListLiveData: MutableLiveData<List<BeerDB>> = MutableLiveData()
    val favoriteLiveData: MutableLiveData<Int> = MutableLiveData()
    val progressBarLiveData: MutableLiveData<Int> = MutableLiveData()

    var filteredBy = 0
    private var dbHandler: Handler? = null
    var hasMorePages = true
    private var page = 1

    companion object {
        private const val BEERS_DEFAULT = 0
        private const val BEERS_ABV = 1
        private const val BEERS_IBU = 2
        private const val BEERS_EBC = 3
    }

    fun getBeers() = viewModelScope.launch(Dispatchers.IO) {
        if (page == 1 && beerListLiveData.value != null && beerListLiveData.value!!.size > 20) {
            page = beerListLiveData.value!!.size / 20 + 1
            hasMorePages = beerListLiveData.value!!.size < 100
        }
        if (hasMorePages.not()) return@launch
        progressBarLiveData.postValue(View.VISIBLE)
        BeerRepository.getBeers(object : OnApiResponse {
            override fun onResult(success: Any?) {
                val beersDB = mutableListOf<BeerDB>()
                filteredBy = BEERS_DEFAULT
                for (beer in (success as List<Beer>)) {
                    val beerDB = beer.getBeerDB()
                    beersDB.add(beerDB)
                }

                getDBHandler()?.post { AppDb.instance?.appDatabase?.beersDao()?.insert(beersDB) }

                beerListLiveData.postValue(beersDB)

                hasMorePages = beersDB.size > 0
                page++
                progressBarLiveData.postValue(View.GONE)
            }

            override fun onError(err: String?) {
                progressBarLiveData.postValue(View.GONE)
            }
        }, page)
    }

    // This method is used to show initial beers list.
    // First we are checking if there are saved beers in local database,
    // and if not we try to get them via API
    private fun getBeersDefault() {
        getDBHandler()?.post {
            val beers = AppDb.instance?.appDatabase?.beersDao()?.beerList()
            if (beers == null || beers.isEmpty()) {
                getBeers()
            } else {
                beerListLiveData.postValue(beers)
            }
        }
    }

    private fun getDBHandler(): Handler? {
        if (dbHandler == null) {
            val mThread = HandlerThread("db-thread")
            mThread.start()
            dbHandler = Handler(mThread.looper)
        }
        return dbHandler
    }

    fun getBeersABV() {
        getDBHandler()?.post {
            filteredBy = BEERS_ABV
            beerListLiveData.postValue(AppDb.instance?.appDatabase?.beersDao()?.beerListAbv())
        }
    }

    fun getBeersIBU() {
        getDBHandler()?.post {
            filteredBy = BEERS_IBU
            beerListLiveData.postValue(AppDb.instance?.appDatabase?.beersDao()?.beerListIbu())
        }
    }

    fun getBeersEBC() {
        getDBHandler()?.post {
            filteredBy = BEERS_EBC
            beerListLiveData.postValue(AppDb.instance?.appDatabase?.beersDao()?.beerListEbc())
        }
    }

    // This method should be called when view is created to get beers and show them into the list
    fun prepareList() {
        when (filteredBy) {
            BEERS_DEFAULT -> getBeersDefault()
            BEERS_ABV -> getBeersABV()
            BEERS_IBU -> getBeersIBU()
            BEERS_EBC -> getBeersEBC()
        }
    }

    fun setFavorite(id: Long, favorite: Boolean?, pos: Int) {
        getDBHandler()?.post {
            AppDb.instance?.appDatabase?.beersDao()?.updateBeer(id, favorite)
            favoriteLiveData.postValue(pos)
        }
    }
}