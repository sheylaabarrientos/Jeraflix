package com.jera.jeraflixx.detailsmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.jera.jeraflixx.R
import com.jera.jeraflixx.constants.Constants
import com.jera.jeraflixx.database.retrofit.Movie
import com.jera.jeraflixx.databinding.DetailsMoviesFragmentBinding
import com.jera.jeraflixx.utils.dateFormatter
import com.jera.jeraflixx.utils.setToolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*

class DetailsMoviesFragment : Fragment() {
    private lateinit var binding: DetailsMoviesFragmentBinding
    private val args: DetailsMoviesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsMoviesFragmentBinding.inflate(inflater, container, false)
        view?.let { setToolbar(it.findViewById(R.id.appBar)) }
        hideButtons()
        insertListeners()
        return binding.root
    }

    private fun bindingView(args: DetailsMoviesFragmentArgs) {
        val movie: Movie = args.movie

        context?.let {
            Glide.with(it)
                .load(Constants.BASE_URL_IMAGE + movie.posterPath)
                .transform(CenterCrop())
                .into(binding.coverMovieDetailsImageView)
        }

        binding.titleMovieDetailTextView.text = movie.title
        binding.ratingDetailMoviesTextView.text = movie.getRating()
        binding.releaseDateTextView.text = movie.releaseDate.dateFormatter()
        binding.overviewDetailTextView.text = movie.overview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingView(args)
    }

    private fun hideButtons() {
        binding.detailsAppBar.appBar.menu.findItem(R.id.search).isVisible = false
        activity?.activity_main_bottomNavigation?.isVisible = false
    }

    private fun insertListeners() {
        binding.detailsAppBar.appBar.setNavigationOnClickListener {
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        findNavController().popBackStack()
        activity?.activity_main_bottomNavigation?.isVisible = true
    }
}


