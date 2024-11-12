package com.example.tugasapi.network

import com.example.tugasapi.model.Chars
import com.example.tugasapi.model.Result
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("neko?amount=20")
    fun getAllChars(): Call<Result>
}