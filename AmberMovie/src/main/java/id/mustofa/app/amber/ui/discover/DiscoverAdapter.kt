package id.mustofa.app.amber.ui.discover

import androidx.databinding.ViewDataBinding
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.SimpleRecyclerAdapter
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.ItemDiscoverBinding

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class DiscoverAdapter : SimpleRecyclerAdapter<Movie>(R.layout.item_discover) {

    override fun getViewHolder(dataBinding: ViewDataBinding) =
        DiscoverViewHolder(dataBinding as ItemDiscoverBinding)

    inner class DiscoverViewHolder(
        private val binding: ItemDiscoverBinding
    ) : Holder(binding.root) {
        override fun setItem(item: Movie) {
            binding.item = item
        }
    }
}