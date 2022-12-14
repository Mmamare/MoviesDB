package com.example.moviesdb.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movie_Table")
data class MoviesEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val genere_ids: List<Int>,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
