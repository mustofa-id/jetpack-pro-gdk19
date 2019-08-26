package id.mustofa.app.amber.ui.discover.movie

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.BindingFragment
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.FragmentMovieBinding
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.ui.discover.DiscoverAdapter
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : BindingFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    private lateinit var adapter: DiscoverAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupAdapter() {
        adapter = DiscoverAdapter()
        adapter.setOnItemClickListener { openDetail(it) }
    }

    private fun setupRecyclerView() {
        val span = context?.resources?.getInteger(R.integer.grid_span_movie) ?: 3
        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = GridLayoutManager(context, span)
        rvMovie.adapter = adapter
    }

    private fun setupViewModel() {
        val fragmentActivity = activity as FragmentActivity
        binding?.viewModel = fragmentActivity.obtainViewModel(MovieViewModel::class)
        binding?.viewModel?.run { fragmentActivity.snackItObserve(message, viewLifecycleOwner) }
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.MOVIE)
        }
    }
}
