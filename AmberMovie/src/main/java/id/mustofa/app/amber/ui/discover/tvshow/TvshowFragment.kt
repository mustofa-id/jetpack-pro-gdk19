package id.mustofa.app.amber.ui.discover.tvshow


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.BindingFragment
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.FragmentDiscoverBinding
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.ui.discover.DiscoverAdapter
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.fragment_discover.*

class TvshowFragment : BindingFragment<FragmentDiscoverBinding>(R.layout.fragment_discover) {

    private val adapter = DiscoverAdapter { openDetail(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        val span = context?.resources?.getInteger(R.integer.grid_span_movie) ?: 3
        rvDiscover.setHasFixedSize(true)
        rvDiscover.layoutManager = GridLayoutManager(context, span)
        rvDiscover.adapter = adapter
    }

    private fun setupViewModel() {
        val fragmentActivity = activity as FragmentActivity
        binding?.viewModel = fragmentActivity.obtainViewModel(TvshowViewModel::class)
        binding?.viewModel?.run { fragmentActivity.snackItObserve(message, viewLifecycleOwner) }
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.TV)
        }
    }
}
