package com.example.tugasapi.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("results") val results: List<Chars>,
)
