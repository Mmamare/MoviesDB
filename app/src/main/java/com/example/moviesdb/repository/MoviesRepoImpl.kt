package com.example.moviesdb.repository

import com.example.moviesdb.api.MoviesApiService
import com.example.moviesdb.common.NetworkManager
import com.example.moviesdb.db.MovieDao
import com.example.moviesdb.db.MoviesEntity
import com.example.moviesdb.model.Result
import com.example.moviesdb.model.UIState
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val moviesApiService: MoviesApiService,
    private val networkManager: NetworkManager): MovieRepository {
    override fun nowPlayingResponseList(api_key: String, page: Int): Flow<UIState> {
        return flow {
            emit(UIState.Loading(true))
            delay(500)
            if (networkManager.isConnected) {
                val response = moviesApiService.getNowPlayingMovie(api_key, page)
                if (response.isSuccessful)
                    response.body()?.let { remote ->
                        remote.map {
                            MoviesEntity(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }.forEach { movie ->
                            movieDao.update(movie)
                        }
                    }
                emit(
                    UIState.ResponseNowPlaying(
                        movieDao.getAllMovies().map {
                            Result(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }
                    )
                )

            }else{
                emit(
                    UIState.ResponseNowPlaying(
                        movieDao.getAllMovies().map {
                            Result(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }
                    )
                )
            }
        }
    }



    override fun popularResponseList(api_key: String, page: Int): Flow<UIState> {
        return flow {
            emit(UIState.Loading(true))
            delay(500)
            if (networkManager.isConnected) {
                val response = moviesApiService.getPopular(api_key, page)
                if (response.isSuccessful)
                    response.body()?.let { remote ->
                        remote.map {
                            MoviesEntity(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }.forEach { movie ->
                            movieDao.update(movie)
                        }
                    }
                emit(
                    UIState.ResponsePopular(
                        movieDao.getAllMovies().map {
                            Result(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }
                    )
                )

            }else{
                emit(
                    UIState.ResponsePopular(
                        movieDao.getAllMovies().map {
                            Result(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }
                    )
                )
            }
        }
    }







    override fun upcomingReesponseList(api_key: String, page: Int): Flow<UIState> {
        return flow {
            emit(UIState.Loading(true))
            delay(500)
            if (networkManager.isConnected) {
                val response = moviesApiService.getUpcoming(api_key, page)
                if (response.isSuccessful)
                    response.body()?.let { remote ->
                        remote.map {
                            MoviesEntity(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }.forEach { movie ->
                            movieDao.update(movie)
                        }
                    }
                emit(
                    UIState.ResponseUpcoming(
                        movieDao.getAllMovies().map {
                            Result(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }
                    )
                )

            }else{
                emit(
                    UIState.ResponseUpcoming(
                        movieDao.getAllMovies().map {
                            Result(
                                adult = it.adult,
                                backdrop_path = it.backdrop_path,
                                genere_ids = it.genere_ids,
                                id = it.id,
                                original_language = it.original_language,
                                original_title = it.original_title,
                                overview = it.overview,
                                popularity = it.popularity,
                                poster_path = it.poster_path,
                                release_date = it.release_date,
                                title = it.title,
                                video = it.video,
                                vote_average = it.vote_average,
                                vote_count = it.vote_count
                            )
                        }
                    )
                )
            }
        }
    }



    override fun movieDetails(api_key: String, page: Int, movie_id: Int): Flow<UIState> {
        return flow {
            emit(UIState.Loading(true))
            delay(500)
            if (networkManager.isConnected){
                val response = moviesApiService.getDetails(api_key, page, movie_id)
                if (response.isSuccessful)
                    response.body()?.let { remote->
                        emit(
                            UIState.MovieDetails(
                                remote.map {
                                    MoviesEntity(
                                        adult = it.adult,
                                        backdrop_path = it.backdrop_path,
                                        genere_ids = it.genere_ids,
                                        id = it.id,
                                        original_language = it.original_language,
                                        original_title = it.original_title,
                                        overview = it.overview,
                                        popularity = it.popularity,
                                        poster_path = it.poster_path,
                                        release_date = it.release_date,
                                        title = it.title,
                                        video = it.video,
                                        vote_average = it.vote_average,
                                        vote_count = it.vote_count
                                    )
                                }
                            )
                        )
                    }
            } else{
                emit(
                    UIState.Error("No Internet collection")
                )
            }
        }
    }
}