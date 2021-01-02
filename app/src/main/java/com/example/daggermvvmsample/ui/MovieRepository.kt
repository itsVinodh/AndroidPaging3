package com.example.daggermvvmsample.ui

import androidx.paging.*
import com.example.daggermvvmsample.adapter.MoviePagingSource
import com.example.daggermvvmsample.api.MovieApi
import com.example.daggermvvmsample.model.Movie
import com.example.daggermvvmsample.model.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi) {

    fun getNowPlayingMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 400,
                enablePlaceholders = false,
                initialLoadSize = 10*3,
                prefetchDistance = 10
            ),
            pagingSourceFactory = { MoviePagingSource(movieApi) }
        ).flow
}