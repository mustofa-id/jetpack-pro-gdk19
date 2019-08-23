package id.mustofa.app.amber.ui.tvshow

import androidx.databinding.ViewDataBinding
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.SimpleRecyclerAdapter
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.ItemTvshowBinding

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class TvshowAdapter : SimpleRecyclerAdapter<Movie>(R.layout.item_tvshow) {

    override fun getViewHolder(dataBinding: ViewDataBinding) =
        TvshowViewHolder(dataBinding as ItemTvshowBinding)

    inner class TvshowViewHolder(private val binding: ItemTvshowBinding) : Holder(binding.root) {
        override fun setItem(item: Movie) {
            binding.tv = item
        }
    }
}