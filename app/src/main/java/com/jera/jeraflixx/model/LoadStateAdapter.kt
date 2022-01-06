package com.jera.jeraflixx.popularmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jera.jeraflixx.databinding.FailSystemFragmentBinding

class PopularMoviesLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PopularMoviesLoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState)
            : PopularMoviesLoadStateViewHolder {
        return PopularMoviesLoadStateViewHolder(
            FailSystemFragmentBinding
                .inflate(LayoutInflater.from(parent.context), parent, false), retry
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}

class PopularMoviesLoadStateViewHolder(
    private val binding: FailSystemFragmentBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {

        binding.retryButton.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsgTextView.text = loadState.error.localizedMessage
        }

        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.errorMsgTextView.isVisible = loadState is LoadState.Error

    }
}
