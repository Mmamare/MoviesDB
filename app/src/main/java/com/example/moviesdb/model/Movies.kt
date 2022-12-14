package com.example.moviesdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genere_ids: List<Int>,
    @SerializedName("id")
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

@Entity(tableName = "Remote_paging_key_Table")
data class RemotePagingKey(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)

