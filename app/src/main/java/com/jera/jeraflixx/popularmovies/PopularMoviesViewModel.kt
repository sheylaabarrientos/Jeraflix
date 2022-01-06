package com.jera.jeraflixx.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jera.jeraflixx.constants.Constants.Companion.NETWORK_PAGE_SIZE
import com.jera.jeraflixx.database.retrofit.Movie
import com.jera.jeraflixx.database.retrofit.MoviesRepository
import kotlinx.coroutines.flow.Flow

class PopularMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getPopularMovies() : Flow<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(repository = repository)
            }
        ).flow.cachedIn(viewModelScope)
    }
}
