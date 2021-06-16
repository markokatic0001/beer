package com.example.beer.rest

import android.util.Log
import com.example.beer.rest.model.Beer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
 * Created by Marko 16 June 2021.
 */
object BeerRepository {
    private const val BASE_URL: String = "https://api.punkapi.com/v2/"
    lateinit var webServiceBeer: WebServiceBeer

    private const val ITEMS_PER_PAGE = 20

    fun getBeers(callback: OnApiResponse, page: Int) {
        try {
            val getBeersCall = webServiceBeer.getBeers(page.toString(), ITEMS_PER_PAGE.toString())
            getBeersCall.enqueue(object : retrofit2.Callback<List<Beer>?> {
                override fun onResponse(
                    call: Call<List<Beer>?>,
                    response: Response<List<Beer>?>
                ) {
                    if (response.isSuccessful) {
                        callback.onResult(response.body())
                    } else {
                        callback.onError(response.errorBody()?.string())
                    }
                }

                override fun onFailure(
                    call: Call<List<Beer>?>,
                    t: Throwable
                ) {
                    callback.onError(t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            Log.e("TAG", "getBeers: ${e.localizedMessage}")
        }
    }

    fun init() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        webServiceBeer = retrofit.create(WebServiceBeer::class.java)
    }

}