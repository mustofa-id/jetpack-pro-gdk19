package id.mustofa.app.amber.ui.favorite.tvshow


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.BindingFragment
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.FragmentFavoriteBinding
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.ui.favorite.FavoriteAdapter
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.fragment_favorite.*

class TvshowFavoriteFragment :
    BindingFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val adapter = FavoriteAdapter { openDetail(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        binding?.viewModel?.fetchMovies()
    }

    private fun setupRecyclerView() {
        rvFavorite.setHasFixedSize(true)
        rvFavorite.layoutManager = LinearLayoutManager(context)
        rvFavorite.adapter = adapter
    }

    private fun setupViewModel() {
        val activity = activity as FragmentActivity
        binding?.run {
            viewModel = activity.obtainViewModel(TvshowFavoriteViewModel::class)
            viewModel?.run { activity.snackItObserve(message, viewLifecycleOwner) }
        }
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.TV)
        }
    }
}
