package id.mustofa.app.academy.ui.academy

import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.mustofa.app.academy.R
import id.mustofa.app.academy.base.BaseRecyclerAdapter
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.util.load
import kotlinx.android.synthetic.main.items_academy.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 03/08/19
 */
class AcademyAdapter : BaseRecyclerAdapter<Course>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return object : ItemHolder(inflate(parent, R.layout.items_academy)) {

            override fun setItem(item: Course) {
                super.setItem(item)
                itemView.run {
                    with(item) {
                        textTitle.text = title
                        textDescription.text = description
                        textDate.text = String.format("Deadline %s", deadline)
                        imgPoster.load(imagePath)
                    }
                }
            }
        }
    }
}