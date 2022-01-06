package com.jera.jeraflixx.toprated

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

class TopRatedMoviesPagingAdapter(var onClick: (movie: Movie) -> Unit = {}) :
    PagingDataAdapter<Movie, TopRatedMoviesPagingAdapter.TopRatedMovieViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMovieViewHolder {
        return TopRatedMovieViewHolder(
            ItemListMoviesBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopRatedMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class TopRatedMovieViewHolder(val binding: ItemListMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(BASE_URL_IMAGE + movie.posterPath)
                .transform(CenterCrop())
                .into(binding.coverMovieImageView)

            binding.coverMovieImageView.setOnClickListener {
                onClick.invoke(movie)
            }

            binding.movieTitleTextView.text = movie.title
            binding.movieRateTextView.text = movie.rating.toString()
            binding.releaseDateTextView.visibility = View.GONE
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