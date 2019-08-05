package id.mustofa.app.amber.ui.tvshow


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
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TvshowFragment : Fragment() {

    private lateinit var viewModel: TvshowViewModel
    private lateinit var adapter: TvshowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this)[TvshowViewModel::class.java]
    }

    private fun setupAdapter() {
        adapter = TvshowAdapter()
        adapter.setOnItemClickListener { openDetail(it) }
    }

    private fun setupRecyclerView() {
        val span = context?.resources?.getInteger(R.integer.grid_span_movie) ?: 3
        rvTvshowFragment.setHasFixedSize(true)
        rvTvshowFragment.layoutManager = GridLayoutManager(context, span)
        rvTvshowFragment.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.populateData(viewModel.getTvshows())
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            it.putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            it.putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.TV)
        }
    }
}
