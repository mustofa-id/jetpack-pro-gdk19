package id.mustofa.app.amber.ui.tvshow

import android.view.View
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.SimpleRecyclerAdapter
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.loadTmdbImage
import kotlinx.android.synthetic.main.item_tvshow.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class TvshowAdapter : SimpleRecyclerAdapter<Movie>(R.layout.item_tvshow) {

    override fun getViewHolder(view: View) = TvshowViewHolder(view)

    inner class TvshowViewHolder(view: View) : Holder(view) {
        override fun setItem(item: Movie) {
            itemView.run {
                imgItemTvshowPoster.loadTmdbImage(item.posterPath)
                textItemTvshowRating.text = String.format("%.1f", item.voteAverage)
                textItemTvshowTitle.text = item.title
            }
        }
    }
}