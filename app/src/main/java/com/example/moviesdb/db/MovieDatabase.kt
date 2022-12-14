package com.example.moviesdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesdb.model.Movies
import com.example.moviesdb.model.RemotePagingKey

@Database(entities = [Result::class, RemotePagingKey::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemotePagingKeyDao
}