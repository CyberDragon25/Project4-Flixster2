package edu.iastate.shubham8.flixter2

data class SeriesDetailsResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val cast: List<Actor>,
    // Add more properties as needed
)

data class Actor(
    val id: Int,
    val name: String,
    val character: String
    // Add more actor properties as needed
)
