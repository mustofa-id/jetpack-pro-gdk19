package id.mustofa.app.amber.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import id.mustofa.app.amber.R
import id.mustofa.app.amber.databinding.ActivityDetailBinding
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbar()
        setupActions()
        setupViewModel()
        loadIntentExtras()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
    }

    private fun setupToolbar() {
        setSupportActionBar(detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupActions() {
        detailFabTrailer.setOnClickListener {
            snackIt(getString(R.string.msg_play_trailer), parent = it)
        }
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel(DetailMovieViewModel::class)
        binding.viewModel = viewModel
        snackItObserve(viewModel.message, this)
        viewModel.movie.observe(this, Observer {
            binding.apply {
                movie = it
                movieContent.movie = it
            }
        })
    }

    private fun loadIntentExtras() {
        intent.run {
            viewModel.type = getSerializableExtra(Const.EXTRA_MOVIE_TYPE) as MediaType
            viewModel.movieId = getLongExtra(Const.EXTRA_MOVIE_ID, -1)
        }
    }
}
