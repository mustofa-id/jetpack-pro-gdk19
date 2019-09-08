package id.mustofa.app.amber.ui.discover.movie

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.BaseFragment
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.ui.discover.DiscoverAdapter
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : BaseFragment(R.layout.fragment_movie) {

    private val adapter = DiscoverAdapter { openDetail(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        val span = context?.resources?.getInteger(R.integer.grid_span_movie) ?: 3
        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = GridLayoutManager(context, span)
        rvMovie.adapter = adapter
    }

    private fun setupViewModel() {
        (activity as FragmentActivity).obtainViewModel(MovieViewModel::class).apply {
            observe(movies) { adapter.submitList(it) }
            observe(loading) { rlMovie.isRefreshing = it }
            observe(message) { rlMovie.snackIt(it) }
            observe(empty) { emptyMovie.visible(it) }
            rlMovie.setOnRefreshListener { fetchMovies(true) }
        }
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.MOVIE)
        }
    }
}
