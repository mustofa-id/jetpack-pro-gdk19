package id.mustofa.app.amber.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.util.Const
import id.mustofa.app.amber.util.MediaType
import id.mustofa.app.amber.util.toActivity
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this)[MovieViewModel::class.java]
    }

    private fun setupAdapter() {
        adapter = MovieAdapter()
        adapter.setOnItemClickListener { openDetail(it) }
    }

    private fun setupRecyclerView() {
        val span = context?.resources?.getInteger(R.integer.grid_span_movie) ?: 3
        rvMovieFragment.setHasFixedSize(true)
        rvMovieFragment.layoutManager = GridLayoutManager(context, span)
        rvMovieFragment.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.populateData(viewModel.getMovies())
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            it.putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            it.putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.MOVIE)
        }
    }
}
