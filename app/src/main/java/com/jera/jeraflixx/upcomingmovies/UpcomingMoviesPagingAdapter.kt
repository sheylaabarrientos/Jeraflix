package com.jera.jeraflixx.upcomingmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.jera.jeraflixx.constants.Constants.Companion.BASE_URL_IMAGE
import com.jera.jeraflixx.database.retrofit.Movie
import com.jera.jeraflixx.databinding.ItemListMoviesBinding
import com.jera.jeraflixx.utils.dateFormatter

class UpcomingMoviesPagingAdapter(var onClick: (movie: Movie) -> Unit = {}) :
    PagingDataAdapter<Movie, UpcomingMoviesPagingAdapter.PopularMovieViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        return PopularMovieViewHolder(
            ItemListMoviesBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class PopularMovieViewHolder(val binding: ItemListMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(BASE_URL_IMAGE + movie.posterPath)
                .transform(CenterCrop())
                .into(binding.coverMovieImageView)

            binding.coverMovieImageView.setOnClickListener {
                onClick.invoke(movie)
            }

            binding.releaseDateTextView.visibility = View.VISIBLE
            binding.movieTitleTextView.text = movie.title
            binding.releaseDateTextView.text = movie.releaseDate.dateFormatter()
            binding.movieRateTextView.visibility = View.GONE
        }
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
