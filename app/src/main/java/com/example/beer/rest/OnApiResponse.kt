package com.example.beer.rest

interface OnApiResponse {

    fun onResult(success: Any?)

    fun onError(err: String?)
}