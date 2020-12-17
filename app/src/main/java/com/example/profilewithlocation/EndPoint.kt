package com.example.profilewithlocation

import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {
    @GET("viewreport.php")
    fun getProfileDetails(): Call<ResponseNew>
}