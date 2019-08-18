package id.mustofa.app.amber.ui.tvshow

import android.view.ViewGroup
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.BaseRecyclerAdapter
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.loadTmdbImage
import kotlinx.android.synthetic.main.item_tvshow.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class TvshowAdapter : BaseRecyclerAdapter<Movie>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return object : Holder(inflate(parent, R.layout.item_tvshow)) {

            override fun setItem(item: Movie) {
                super.setItem(item)
                itemView.run {
                    imgItemTvshowPoster.loadTmdbImage(item.posterPath)
                    textItemTvshowRating.text = String.format("%.1f", item.voteAverage)
                    textItemTvshowTitle.text = item.title
                }
            }
        }
    }
}