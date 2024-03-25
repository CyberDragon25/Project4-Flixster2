package edu.iastate.shubham8.flixter2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.request.RequestOptions

class SeriesAdapter(private val context: Context, private val seriesList: MutableList<Series>) :
    RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = seriesList[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    fun setData(newSeriesList: List<Series>) {
        seriesList.clear()
        seriesList.addAll(newSeriesList)
        notifyDataSetChanged()
    }

    inner class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)

        fun bind(series: Series) {
            titleTextView.text = series.name
            // Load series poster image using Glide
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500${series.posterPath}")
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(posterImageView)

            itemView.setOnClickListener {
                // Retrieve the clicked series
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedSeries = seriesList[position]

                    // Start the detail activity and pass relevant data
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("series_id", clickedSeries.id)
                        putExtra("series_title", clickedSeries.name)
                        // Add more data as needed
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}
