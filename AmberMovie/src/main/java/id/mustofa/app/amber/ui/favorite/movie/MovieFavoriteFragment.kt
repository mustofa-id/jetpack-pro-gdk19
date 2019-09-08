package id.mustofa.app.amber.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.BaseFragment
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.ui.favorite.FavoriteAdapter
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFavoriteFragment : BaseFragment(R.layout.fragment_movie) {

    private lateinit var viewModel: MovieFavoriteViewModel
    private val adapter = FavoriteAdapter { openDetail(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchMovies()
    }

    private fun setupRecyclerView() {
        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = LinearLayoutManager(context)
        rvMovie.adapter = adapter
    }

    private fun setupViewModel() {
        val activity = activity as FragmentActivity
        viewModel = activity.obtainViewModel(MovieFavoriteViewModel::class)
        viewModel.apply {
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
