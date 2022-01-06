package com.jera.jeraflixx.upcomingmovies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jera.jeraflixx.constants.Constants.Companion.STARTING_PAGE
import com.jera.jeraflixx.database.retrofit.Movie
import com.jera.jeraflixx.database.retrofit.MoviesRepository
import com.jera.jeraflixx.model.ExceptionModel
import com.jera.jeraflixx.model.NetworkResult


class UpcomingMoviesPagingSource(private val repository: MoviesRepository) :
    PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            try {
                val response = repository.getUpcomingMovies(params.key ?: STARTING_PAGE)
                return when (response) {
                    is NetworkResult.Success -> {
                        LoadResult.Page(
                            data = response.data.movies,
                            prevKey = params.key,
                            nextKey = params.key?.plus(1) ?: STARTING_PAGE.plus(1)
                        )
                    }
                    is NetworkResult.Error -> {
                        LoadResult.Error(ExceptionModel())
                    }
                }
            } catch (exception: Throwable) {
                return LoadResult.Error(exception)
            }

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPostion ->
            state.closestPageToPosition(anchorPostion)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPostion)?.nextKey?.minus(1)
        }
    }
}
