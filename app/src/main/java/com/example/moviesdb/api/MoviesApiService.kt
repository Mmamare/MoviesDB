package com.example.moviesdb.api

import com.example.moviesdb.db.MoviesEntity
import com.example.moviesdb.model.Movies
import com.example.moviesdb.model.Result
import retrofit2.Response
import retrofit2.http.*

interface MoviesApiService {
    @GET("now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<List<Result>>

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<List<Result>>

    @Headers("Content-Type")
    @POST("{movie_id}/rating")
    suspend fun getRating(
        @Query("api_key") api_key: String,
        @Path("movie_id") movie_id: Int
    ): Response<List<Result>>

    @GET("upcoming")
    suspend fun getUpcoming(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<List<Result>>

    @GET("{movie_id}")
    suspend fun getDetails(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Path("movie_id") movie_id: Int
    ): Response<List<Result>>
}