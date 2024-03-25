package edu.iastate.shubham8.flixter2

import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    @SerializedName("results") val results: List<Series>
)