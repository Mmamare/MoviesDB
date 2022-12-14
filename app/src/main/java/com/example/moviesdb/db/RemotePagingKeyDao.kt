package com.example.moviesdb.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesdb.model.RemotePagingKey

interface RemotePagingKeyDao {
    @Query("Select * from Remote_paging_key_Table where id =:id")
    suspend fun getRemotekey(id: Int): RemotePagingKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemotekeys(remotePagingKey: List<RemotePagingKey>)

    @Query("delete from Remote_paging_key_Table")
    suspend fun deleteRemoteKey()
}