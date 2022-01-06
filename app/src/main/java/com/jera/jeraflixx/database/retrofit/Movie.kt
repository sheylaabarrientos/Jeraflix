package com.jera.jeraflixx.database.retrofit

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("overview") val overview: String
) : Serializable {
    fun getRating(): String {
        val rating = (rating * 10).toInt()
        return "$rating%"
    }
}
