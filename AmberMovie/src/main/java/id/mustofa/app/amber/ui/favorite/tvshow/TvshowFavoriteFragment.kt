package id.mustofa.app.amber.ui.favorite.tvshow


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.BindingFragment
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.FragmentMovieBinding
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.ui.favorite.FavoriteAdapter
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.fragment_movie.*

class TvshowFavoriteFragment : BindingFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    private lateinit var adapter: FavoriteAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupAdapter() {
        adapter = FavoriteAdapter()
        adapter.setOnItemClickListener { openDetail(it) }
    }

    private fun setupRecyclerView() {
        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = LinearLayoutManager(context)
        rvMovie.adapter = adapter
    }

    private fun setupViewModel() {
        val fragmentActivity = activity as FragmentActivity
        binding?.viewModel = fragmentActivity.obtainViewModel(TvshowFavoriteViewModel::class)
        binding?.viewModel?.run { fragmentActivity.snackItObserve(message, viewLifecycleOwner) }
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.TV)
        }
    }

}
