package edu.iastate.shubham8.flixter2

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve data passed from MainActivity
        val seriesId = intent.getIntExtra("series_id", -1)
        val seriesTitle = intent.getStringExtra("series_title")
        val seriesPosterPath = intent.getStringExtra("series_poster_path")
        val seriesRating = intent.getFloatExtra("series_rating", 0f)

        // Find views in layout
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val posterImageView: ImageView = findViewById(R.id.posterImageView)
        val ratingTextView: TextView = findViewById(R.id.ratingTextView)

        // Set series title and rating
        titleTextView.text = seriesTitle
        ratingTextView.text = "Rating: $seriesRating"

        // Load series poster image using Glide
        if (!seriesPosterPath.isNullOrEmpty()) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500$seriesPosterPath")
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(posterImageView)
        } else {
            // Handle case where seriesPosterPath is null or empty
            // For example, set a default image or hide the ImageView
            posterImageView.setImageResource(R.drawable.ic_launcher_background)
            // Or
            // posterImageView.visibility = View.GONE
        }
        // Fetch additional series details from API
        fetchSeriesDetails(seriesId)
    }

    private fun fetchSeriesDetails(seriesId: Int) {
        // Make API call to fetch additional details about the TV series using seriesId
        // For example:
        val apiService = RetrofitClient.create()
        val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed" // Replace with your actual TMDb API key

        val call: Call<SeriesDetailsResponse> = apiService.getSeriesDetails(seriesId, apiKey)
        call.enqueue(object : Callback<SeriesDetailsResponse> {
            override fun onResponse(call: Call<SeriesDetailsResponse>, response: Response<SeriesDetailsResponse>) {
                if (response.isSuccessful) {
                    val seriesDetailsResponse = response.body()
                    // Process seriesDetailsResponse containing additional details about the series
                    // Update UI to display additional series details
                } else {
                    // Handle API error
                    // For example, display an error message to the user
                }
            }

            override fun onFailure(call: Call<SeriesDetailsResponse>, t: Throwable) {
                // Handle network failure
                // For example, display a network error message to the user
            }
        })
    }

    fun onBackButtonClicked(view: View) {
        // Navigate back to MainActivity
        onBackPressed()
    }
}
