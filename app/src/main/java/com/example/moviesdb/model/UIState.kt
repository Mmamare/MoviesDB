package com.example.moviesdb.model

import com.example.moviesdb.db.MoviesEntity

sealed class UIState{
    data class ResponseNowPlaying(val data: List<Result>): UIState()
    data class ResponsePopular(val data: List<Result>): UIState()
    data class ResponseUpcoming(val data: List<Result>): UIState()
    data class MovieDetails(val data: List<MoviesEntity>): UIState()
    data class Error(val errorMessage: String): UIState()
    data class Loading(val isLoading: Boolean = false): UIState()

}
