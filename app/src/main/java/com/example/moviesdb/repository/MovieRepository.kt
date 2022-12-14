package com.example.moviesdb.repository

import com.example.moviesdb.model.UIState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun nowPlayingResponseList(api_key: String, page: Int): Flow<UIState>
    fun popularResponseList(api_key: String, page: Int): Flow<UIState>
    fun upcomingReesponseList(api_key: String, page: Int): Flow<UIState>
    fun movieDetails(api_key: String, page: Int, movie_id: Int): Flow<UIState>
}