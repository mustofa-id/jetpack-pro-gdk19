package id.mustofa.app.academy.ui.bookmark

import android.view.ViewGroup
import id.mustofa.app.academy.R
import id.mustofa.app.academy.base.BaseRecyclerAdapter
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.util.load
import kotlinx.android.synthetic.main.items_bookmark.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 03/08/19
 */
class BookmarkAdapter : BaseRecyclerAdapter<Course>() {

    private var shareClickListener: ((Course) -> Unit)? = null

    fun setShareClickListener(listener: (Course) -> Unit) {
        this.shareClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return object : ItemHolder(inflate(parent, R.layout.items_bookmark)) {

            override fun setItem(item: Course) {
                super.setItem(item)
                itemView.run {
                    with(item) {
                        textTitle.text = title
                        textDate.text = String.format("Deadline %s", deadline)
                        textDescription.text = description
                        imgPoster.load(imagePath)
                    }
                    shareClickListener?.let { imgShare.setOnClickListener { it(item) } }
                }
            }
        }
    }
}