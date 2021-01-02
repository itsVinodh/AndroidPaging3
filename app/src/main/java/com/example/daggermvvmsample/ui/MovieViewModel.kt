package com.example.daggermvvmsample.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.daggermvvmsample.model.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class MovieViewModel @ViewModelInject constructor(movieRepository: MovieRepository): ViewModel() {


    val movies: Flow<PagingData<UiModel>> = movieRepository.getNowPlayingMovies()
        .map { pagingData ->  pagingData.filter { movie ->  movie.originalTitle.contains("the", true)}}
        .map { pagingData -> pagingData.map { UiModel.Movie(it) } }
        .map {
            it.insertSeparators<UiModel.Movie, UiModel> { before, after ->
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators UiModel.Separator(after!!.movie.originalTitle)
                }
                else {
                    // no separator
                    null
                }
            }
        }
        .cachedIn(viewModelScope)

}