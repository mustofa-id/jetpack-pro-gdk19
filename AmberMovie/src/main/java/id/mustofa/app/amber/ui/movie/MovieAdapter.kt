package id.mustofa.app.amber.ui.movie

import android.view.View
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.SimpleRecyclerAdapter
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.loadTmdbImage
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class MovieAdapter : SimpleRecyclerAdapter<Movie>(R.layout.item_movie) {

    override fun getViewHolder(view: View) = MovieViewHolder(view)

    inner class MovieViewHolder(view: View) : Holder(view) {
        override fun setItem(item: Movie) {
            itemView.run {
                imgItemMoviePoster.loadTmdbImage(item.posterPath)
                textItemMovieRating.text = item.voteAverage.toString()
                textItemMovieTitle.text = item.title
            }
        }
    }
}