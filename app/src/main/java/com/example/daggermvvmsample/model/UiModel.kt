package com.example.daggermvvmsample.model

sealed class UiModel {
    data class Movie(val movie: com.example.daggermvvmsample.model.Movie) : UiModel()
    data class Separator(val originalTitle: String) : UiModel()
}