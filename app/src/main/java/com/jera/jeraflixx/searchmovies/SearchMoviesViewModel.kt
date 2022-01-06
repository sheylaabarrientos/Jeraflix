package com.jera.jeraflixx.searchmovies

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
import java.net.URLEncoder

class SearchMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchMoviesPagingSource(repository = repository, encoder(query))
            }
        ).flow.cachedIn(viewModelScope)
    }

    private fun encoder(query: String): String {
        return URLEncoder.encode(query, "utf-8")
    }
}
