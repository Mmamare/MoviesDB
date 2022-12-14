package com.example.moviesdb.db

import androidx.paging.PagingSource
import androidx.room.*
import com.example.moviesdb.model.Movies

@Dao
interface MovieDao {
    @Query("Select * From Movie_Table")
    suspend fun getAllMovies(): List<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<MoviesEntity>)

    @Update(entity = Result::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(moviesEntirty: MoviesEntity)

    @Query("Delete from Movie_Table")
    suspend fun deleteMovie()
}