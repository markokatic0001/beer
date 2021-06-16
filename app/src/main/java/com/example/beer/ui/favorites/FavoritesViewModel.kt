package com.example.beer.ui.favorites

import android.app.Application
import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beer.room.AppDb
import com.example.beer.room.BeerDB

/*
 * Created by Marko 16 June 2021.
 */
class FavoritesViewModel(val app: Application) : ViewModel() {

    val favoritesLiveData: MutableLiveData<List<BeerDB>> = MutableLiveData()
    private var dbHandler: Handler? = null

    //This one loads favorite beers from local DB
    fun loadFavoritesFromDB() {
        getDBHandler()?.post {
            val beers = AppDb.instance?.appDatabase?.beersDao()?.favorites()
            favoritesLiveData.postValue(beers)
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
}