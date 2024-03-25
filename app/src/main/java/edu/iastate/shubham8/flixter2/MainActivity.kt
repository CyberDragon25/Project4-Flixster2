package edu.iastate.shubham8.flixter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = SeriesAdapter(this, mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Create Retrofit service instance
        val apiService = RetrofitClient.create()

        // Replace "your_api_key_here" with your actual TMDb API key
        val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

        // Make API call to get on-the-air TV series
       val call: Call<SeriesResponse> = apiService.getOnTheAirSeries()
        call.enqueue(object : Callback<SeriesResponse> {
            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                if (response.isSuccessful) {
                    val seriesResponse = response.body()
                    // Process seriesResponse containing list of TV series
                    val seriesList = seriesResponse?.results ?: emptyList()
                    // Now you can update your RecyclerView adapter with this list of series
                    adapter.setData(seriesList)
                } else {
                    // Handle API error
                    // For example, display an error message to the user
                }
            }

            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                // Handle network failure
                // For example, display a network error message to the user
            }
        })
    }
}