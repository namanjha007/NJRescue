package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface CovidService{

    @GET("data.json")
    fun getNationData() : Call<NationWise>

}

