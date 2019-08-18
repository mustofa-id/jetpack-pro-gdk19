package id.mustofa.app.amber.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
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
        viewModel.movie.observe(this, Observer { populateMovie(it) })
        viewModel.message.observe(this, Observer { snackIt(getString(it)) })
        viewModel.loading.observe(this, Observer {
            pbDetailMovie.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun loadIntentExtras() {
        intent.run {
            viewModel.type = getSerializableExtra(Const.EXTRA_MOVIE_TYPE) as MediaType
            viewModel.movieId = getLongExtra(Const.EXTRA_MOVIE_ID, -1)
        }
    }

    private fun populateMovie(movie: Movie) {
        val self = this@DetailMovieActivity
        movie.run {
            detailToolbarLayout.title = title
            imgMovieDetailBackdrop.loadTmdbImage(backdropPath)
            imgMovieDetailPoster.loadTmdbImage(posterPath)
            textMovieDetailTitle.text = title
            textMovieDetailDate.text = releaseDate
            rateMovieDetailRating.rating = voteAverage / 2
            textMovieDetailOverview.text = overview
            genres.forEach {
                val chip = Chip(self).apply {
                    text = it.name
                    isClickable = false
                    setTextColor(Color.DKGRAY)
                }
                cgMovieDetailGenres.addView(chip)
            }
        }
    }
}
