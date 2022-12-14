package com.example.moviesdb.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviesdb.api.MoviesApiService
import com.example.moviesdb.common.API_KEY
import com.example.moviesdb.common.ITEMS_PER_PAGE
import com.example.moviesdb.db.MovieDao
import com.example.moviesdb.db.MovieDatabase
import com.example.moviesdb.db.MoviesEntity
import com.example.moviesdb.model.Movies
import com.example.moviesdb.model.RemotePagingKey
import retrofit2.Response
import javax.inject.Inject
import kotlin.properties.Delegates

@ExperimentalPagingApi
class MoviesRemoteMediator (
    private val moviesApiService: MoviesApiService,
    private val movieDatabase: MovieDatabase): RemoteMediator<Int, MoviesEntity>() {

    private val movieDao = movieDatabase.movieDao()
    private val remotePagingKeyDao = movieDatabase.remoteKeyDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MoviesEntity>,
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remotePagingKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remotePagingKey?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remotePagingKey != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remotePagingKey = getRemoteKeyForLastItem(state)
                    val nextPage = remotePagingKey?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remotePagingKey != null
                        )
                    nextPage
                }
            }
            val response = moviesApiService.getNowPlayingMovie(api_key = " ", page = currentPage)
            val endOfPaginationReached = response.isEmpty()
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            movieDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDao.deleteMovie()
                    remotePagingKeyDao.deleteRemoteKey()
                }
                val keys = response.map { unsplashImage ->
                    RemotePagingKey(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remotePagingKeyDao.addAllRemotekeys(remotePagingKey = keys)
                movieDao.addMovies(movies = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
        }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MoviesEntity>
    ): RemotePagingKey? {
        return state.anchorPosition?.let { position->
            state.closestItemToPosition(position)?.id?.let { id->
                remotePagingKeyDao.getRemotekey(id = id)
            }
        }

    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MoviesEntity>
    ): RemotePagingKey? {
        return state.pages.firstOrNull(){it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { moviesEntity -> remotePagingKeyDao.getRemotekey(id = moviesEntity.id)

        }

        }



    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MoviesEntity>):
            RemotePagingKey? {
        return state.pages.lastOrNull(){it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { moviesEntity ->
                remotePagingKeyDao.getRemotekey(id = moviesEntity.id)
            }

    }


}