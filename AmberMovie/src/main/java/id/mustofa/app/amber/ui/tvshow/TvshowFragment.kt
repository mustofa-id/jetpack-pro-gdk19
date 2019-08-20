package id.mustofa.app.amber.ui.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.ui.detail.DetailMovieActivity
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.fragment_tvshow.*

// TODO: setup viewModel & observers may can simply using extension
class TvshowFragment : Fragment() {

    private lateinit var viewModel: TvshowViewModel
    private lateinit var adapter: TvshowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupRecyclerView()
        setupViewModel()
        setupRefreshLayout()
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

    private fun setupViewModel() {
        // activity has been created here
        viewModel = activity!!.obtainViewModel(TvshowViewModel::class)

        // subscribe observer
        // NOTE: Using viewLifecycleOwner instead of fragment to avoid leaking liveData observers
        val owner = viewLifecycleOwner
        viewModel.allTvshows.observe(owner, Observer { adapter.populateData(it) })
        viewModel.loading.observe(owner, Observer { srTvshowFragment.isRefreshing = it })
        viewModel.message.observe(owner, Observer { activity?.snackIt(getString(it)) })
        viewModel.empty.observe(owner, Observer {
            textTvshowFragmentMessage.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun setupRefreshLayout() {
        srTvshowFragment.setOnRefreshListener { viewModel.fetchAllTvshows(true) }
    }

    private fun openDetail(movie: Movie) {
        activity?.toActivity(DetailMovieActivity::class) {
            putExtra(Const.EXTRA_MOVIE_ID, movie.id)
            putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.TV)
        }
    }
}
