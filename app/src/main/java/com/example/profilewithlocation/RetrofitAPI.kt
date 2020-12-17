package com.example.profilewithlocation

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {
    private val BASE_URL="http://aryu.co.in/tracking/"
    val instance: EndPoint by lazy {
        val retrofit= Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build()
        retrofit.create(EndPoint::class.java)
    }

}