package com.william.template.ui.movie.popular

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.william.template.R
import com.william.template.databinding.FragmentPopularMovieBinding
import com.william.template.network.TmdbApi
import com.william.template.ui.themeinfo.ThemeInfoBottomSheet
import com.william.template.utils.ThemeHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularMovieFragment : Fragment() {

    private val popularMovieViewModel by viewModels<PopularMovieViewModel>()

    @Inject
    lateinit var themeHelper: ThemeHelper

    @Inject
    lateinit var tmdbApi: TmdbApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPopularMovieBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        val adapter = PopularMovieAdapter()
        binding.movieList.adapter = adapter

        popularMovieViewModel.popularMovieList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.theme_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_theme -> {
                ThemeInfoBottomSheet().show(
                    childFragmentManager,
                    ThemeInfoBottomSheet::class.simpleName
                )
            }
        }
        return true
    }
}