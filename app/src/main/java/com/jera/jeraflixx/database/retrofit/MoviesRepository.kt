package com.jera.jeraflixx.database.retrofit

import com.jera.jeraflixx.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MoviesRepository(private val movieService: MovieService) : ApiResponse() {


    suspend fun getPopularMovies(page: Int): NetworkResult<MoviesResponse> {
           return safeApiCall { movieService.getPopularMovies(page = page) }
    }

    suspend fun getUpcomingMovies(page: Int): NetworkResult<MoviesResponse> {
        return safeApiCall { movieService.getUpcomingMovies(page = page) }
    }

    suspend fun searchMovies(page: Int, query: String?): NetworkResult<MoviesResponse> {
        return safeApiCall { movieService.searchMovies(page = page, query = query) }
    }

    suspend fun getDetailsMovies(page: Int): Flow<NetworkResult<MoviesResponse>> {
        return flow<NetworkResult<MoviesResponse>> {
            emit(safeApiCall { movieService.getDetailsMovies(page = page) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopRatedMovies(page: Int): NetworkResult<MoviesResponse>{
        return safeApiCall { movieService.getTopRated(page = page) }

    }

}