package id.mustofa.app.amber.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import id.mustofa.app.amber.R
import id.mustofa.app.amber.util.Const
import id.mustofa.app.amber.util.MediaType
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setupToolbar()
        setupViewModel()
        loadExtras()
        populateMovie()
    }

    private fun setupToolbar() {
        setSupportActionBar(detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this)[DetailMovieViewModel::class.java]
    }

    private fun loadExtras() {
        intent.run {
            viewModel.movieId = getLongExtra(Const.EXTRA_MOVIE_ID, -1)
            viewModel.type = getSerializableExtra(Const.EXTRA_MOVIE_TYPE) as MediaType
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun populateMovie() {
        val movie = viewModel.getMovie()
    }
}
