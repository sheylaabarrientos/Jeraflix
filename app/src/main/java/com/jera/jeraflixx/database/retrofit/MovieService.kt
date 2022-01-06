package com.jera.jeraflixx.database.retrofit

import com.jera.jeraflixx.constants.Constants.Companion.API_KEY
import com.jera.jeraflixx.constants.Constants.Companion.BASE_LANGUAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = BASE_LANGUAGE
    ): Response<MoviesResponse>

    @GET("/movie/{movie_id}")
    suspend fun getDetailsMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = BASE_LANGUAGE
    ): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = BASE_LANGUAGE,
        @Query("voteAverage") voteAverage: Float = 0.000f
    ): Response<MoviesResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String?,
        @Query("page") page: Int,
        @Query("language") language: String = BASE_LANGUAGE
    ): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") pt_br: String = BASE_LANGUAGE
    ): Response<MoviesResponse>

//    @GET("search/movie")
//    fun searchMovies (
//        @Query("api_key") apiKey: String = "7082818e866b5e8acc5bf39d3f78301a",
//        @Query("query") search: String?,
//        @Query("page") page: Int
//    ) : Call<MoviesResponse>
//
//
//    @GET("movie/top_rated")
//    fun getTopRated (
//        @Query("api_key") apiKey: String = "7082818e866b5e8acc5bf39d3f78301a",
//        @Query("page") page: Int
//
//    ) : Call<MoviesResponse>

}