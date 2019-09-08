package id.mustofa.app.amber.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupToolbar()
        setupActions()
        setupViewModel()
        loadIntentExtras()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupActions() {
        detailFabTrailer.setOnClickListener { it.snackIt(R.string.msg_play_trailer) }
        btnFavorite.setOnClickListener { viewModel.toggleFavorite() }
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel(DetailMovieViewModel::class).apply {
            observe(movie) { populateMovie(it) }
            observe(message) { detailFabTrailer.snackIt(it) }
            observe(favoriteIcon) { btnFavorite.setImageResource(it) }
            observe(loading) {
                pbDetailMovie.visible(it)
                detailAppBar.visible(!it)
                contentDetail.visible(!it)
            }
        }
    }

    private fun populateMovie(movie: Movie) {
        with(movie) {
            detailToolbarLayout.title = title
            imgMovieDetailBackdrop.loadTmdbImage(backdropPath)
            imgMovieDetailPoster.loadTmdbImage(posterPath)
            rateMovieDetailRating.rating = voteAverage / 2
            textMovieDetailTitle.text = title
            textMovieDetailDate.text = releaseDate
            textMovieDetailOverview.text = overview
            cgMovieDetailGenres.apply {
                genres.forEach {
                    addView(Chip(this@DetailMovieActivity).apply {
                        text = it.name
                        isClickable = false
                        setTextColor(Color.DKGRAY)
                    })
                }
            }
        }
    }

    private fun loadIntentExtras() {
        intent.run {
            viewModel.type = getSerializableExtra(Const.EXTRA_MOVIE_TYPE) as MediaType
            viewModel.movieId = getLongExtra(Const.EXTRA_MOVIE_ID, -1)
            viewModel.fetchMovie()
        }
    }
}
