package com.example.gitrepo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    private const val baseURL = "https://developer.github.com/v3/ "

    fun getRetroInstance(): RetroService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RetroService::class.java)
    }
}