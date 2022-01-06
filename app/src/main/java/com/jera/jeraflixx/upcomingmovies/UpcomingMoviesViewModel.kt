package com.jera.jeraflixx.upcomingmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jera.jeraflixx.constants.Constants
import com.jera.jeraflixx.database.retrofit.Movie
import com.jera.jeraflixx.database.retrofit.MoviesRepository
import kotlinx.coroutines.flow.Flow

class UpcomingMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UpcomingMoviesPagingSource(repository = repository)
            }
        ).flow.cachedIn(viewModelScope)
    }
}