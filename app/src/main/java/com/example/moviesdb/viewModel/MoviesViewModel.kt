package com.example.moviesdb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.model.UIState
import com.example.moviesdb.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository,
private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {
    private val _nowPlayingList = MutableLiveData<UIState>()
    val nowPlayingList: LiveData<UIState>
    get() = _nowPlayingList

    private val _popularMovieList = MutableLiveData<UIState>()
    val popularMovieList: LiveData<UIState>
    get() = _popularMovieList

    private val _upcomingMovies = MutableLiveData<UIState>()
    val upcomingMovies: LiveData<UIState>
    get() = _upcomingMovies

    private val  _movieDetail = MutableLiveData<UIState>()
    val movieDetails: LiveData<UIState>
    get() = _movieDetail

    init {}

    private fun getNowPlayingMovie(api_key: String, page:Int){
        CoroutineScope(ioDispatcher).launch {
            repository.nowPlayingResponseList(api_key, page)
                .collect{
                    _nowPlayingList.postValue(it)
                }
        }
    }

    private fun getPopular(api_key: String, page:Int){
        CoroutineScope(ioDispatcher).launch {
           repository.popularResponseList(api_key, page)
               .collect{
                   _popularMovieList.postValue(it)
               }
        }
    }
    private fun getUpcomingMovie(api_key: String, page:Int){
        CoroutineScope(ioDispatcher).launch {
            repository.upcomingReesponseList(api_key, page)
                .collect{
                    _upcomingMovies.postValue(it)
                }
        }
    }

    private fun showDetails(api_key: String, page: Int, movie_id: Int){
        CoroutineScope(ioDispatcher).launch {
            repository.movieDetails(api_key, page, movie_id)
                .collect{
                    _movieDetail.postValue(it)
                }
        }
    }
}