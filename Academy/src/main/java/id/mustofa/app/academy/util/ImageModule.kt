package id.mustofa.app.academy.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import id.mustofa.app.academy.R

/**
 * @author Habib Mustofa
 * Indonesia on 03/08/19
 */
@GlideModule
class ImageModule : AppGlideModule()

fun ImageView.load(imagePath: String) {
    Glide.with(rootView)
        .load(imagePath).apply(
            RequestOptions()
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
        ).into(this)
}