package com.example.gitrepo.network

import com.example.gitrepo.model.gitmodel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("search/repositories")
    fun getDataFromAPI(@Query("q") query: String): Call<gitmodel>

}