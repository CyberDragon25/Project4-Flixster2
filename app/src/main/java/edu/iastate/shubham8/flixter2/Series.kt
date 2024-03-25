package edu.iastate.shubham8.flixter2

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val voteAverage: Float,
    // Add other properties as needed
)