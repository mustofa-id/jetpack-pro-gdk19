package id.mustofa.app.amber.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.FragmentMovieBinding
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupRecyclerView()
        setupViewModel()
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

    private fun setupViewModel() {
        val fragmentActivity = activity as FragmentActivity
        binding.viewModel = fragmentActivity.obtainViewModel(MovieViewModel::class)
        binding.viewModel?.run { fragmentActivity.snackItObserve(message, viewLifecycleOwner) }
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.MOVIE)
        }
    }
}
