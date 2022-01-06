package com.jera.jeraflixx.toprated

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jera.jeraflixx.constants.Constants
import com.jera.jeraflixx.database.retrofit.Movie
import com.jera.jeraflixx.database.retrofit.MoviesRepository
import com.jera.jeraflixx.pagging.TopRatedMoviesPagingSource
import kotlinx.coroutines.flow.Flow

class TopRatedMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TopRatedMoviesPagingSource(repository = repository)
            }
        ).flow
    }
}