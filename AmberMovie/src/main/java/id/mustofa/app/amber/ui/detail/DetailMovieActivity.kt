package id.mustofa.app.amber.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import id.mustofa.app.amber.R
import id.mustofa.app.amber.util.Const
import id.mustofa.app.amber.util.MediaType
import id.mustofa.app.amber.util.load
import id.mustofa.app.amber.util.snackIt
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setupToolbar()
        setupViewModel()
        loadExtras()
        setupActions()
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

    private fun setupActions() {
        detailFabTrailer.setOnClickListener { snackIt(getString(R.string.msg_play_trailer)) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun populateMovie() {
        val self = this@DetailMovieActivity
        val movie = viewModel.getMovie()
        movie.run {
            self.title = title
            imgMovieDetailBackdrop.load(posterResId)
            imgMovieDetailPoster.load(posterResId)
            textMovieDetailTitle.text = title
            textMovieDetailDate.text = releaseDate
            rateMovieDetailRating.rating = voteAverage / 2
            textMovieDetailOverview.text = overview
            genres.forEach {
                val chip = Chip(self).apply {
                    text = it
                    isClickable = false
                    setTextColor(Color.DKGRAY)
                }
                cgMovieDetailGenres.addView(chip)
            }
        }
    }
}
