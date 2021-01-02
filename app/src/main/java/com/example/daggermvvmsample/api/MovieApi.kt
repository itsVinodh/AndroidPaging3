package com.example.daggermvvmsample.api

import com.example.daggermvvmsample.BuildConfig
import com.example.daggermvvmsample.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}")
    suspend fun getNowPlayingMovies(@Query("page") position: Int): MovieResponse
}