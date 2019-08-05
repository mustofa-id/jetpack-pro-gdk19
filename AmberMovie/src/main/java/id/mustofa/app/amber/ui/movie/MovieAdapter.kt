package id.mustofa.app.amber.ui.movie

import android.view.ViewGroup
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.BaseRecyclerAdapter
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.load
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class MovieAdapter : BaseRecyclerAdapter<Movie>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return object : Holder(inflate(parent, R.layout.item_movie)) {

            override fun setItem(item: Movie) {
                super.setItem(item)
                itemView.run {
                    imgItemMoviePoster.load(item.posterResId)
                    textItemMovieRating.text = item.voteAverage.toString()
                    textItemMovieTitle.text = item.title
                }
            }
        }
    }
}