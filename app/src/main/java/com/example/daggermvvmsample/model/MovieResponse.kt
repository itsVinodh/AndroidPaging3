package com.example.daggermvvmsample.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val result: ArrayList<Movie>)