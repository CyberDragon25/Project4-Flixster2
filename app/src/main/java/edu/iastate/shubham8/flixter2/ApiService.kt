package edu.iastate.shubham8.flixter2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("tv/on_the_air")
    fun getOnTheAirSeries(@Query("api_key") apiKey: String = API_KEY): Call<SeriesResponse>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("tv/{id}")
    fun getSeriesDetails(
        @Path("id") seriesId: Int,
        @Query("api_key") apiKey: String
    ): Call<SeriesDetailsResponse>
}
