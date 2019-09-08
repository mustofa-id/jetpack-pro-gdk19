package id.mustofa.app.amber.ui.favorite

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.inflate
import id.mustofa.app.amber.util.loadTmdbImage
import kotlinx.android.synthetic.main.item_favorite.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
class FavoriteAdapter(private val itemCallback: (Movie) -> Unit) :
    PagedListAdapter<Movie, FavoriteAdapter.MovieView>(Movie.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieView {
        return MovieView(parent.inflate(R.layout.item_favorite)).apply {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.let { movie -> itemCallback(movie) }
            }
        }
    }

    override fun onBindViewHolder(holder: MovieView, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieView(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie?) {
            with(itemView) {
                movie?.run {
                    imgItemMoviePoster.loadTmdbImage(posterPath)
                    rateItemMovieRating.rating = voteAverage / 2
                    textItemMovieDate.text = releaseDate
                    textItemMovieTitle.text = title
                    textItemMovieRating.text = String.format("%s", voteAverage)
                    textItemMovieOverview.text = overview
                }
            }
        }
    }
}