package com.example.beer.rest

/*
 * Created by Marko 16 June 2021.
 */
interface OnApiResponse {

    fun onResult(success: Any?)

    fun onError(err: String?)
}