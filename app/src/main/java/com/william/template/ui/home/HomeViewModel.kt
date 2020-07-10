package com.william.template.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.william.template.network.TmdbApi
import com.william.template.network.dto.TmdbMovie
import kotlinx.coroutines.*

class HomeViewModel @ViewModelInject constructor(
    private val tmdbApi: TmdbApi,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _popularMovieList = MutableLiveData<List<TmdbMovie>>()
    val popularMovieList: LiveData<List<TmdbMovie>> = _popularMovieList

    init {
        viewModelScope.launch {
            val movieList = withContext(Dispatchers.IO) {
                val popularMovies = tmdbApi.getPopularMovies()
                popularMovies.movieList
            }
            _popularMovieList.value = movieList
        }
    }
}