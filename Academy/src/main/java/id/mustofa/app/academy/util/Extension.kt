package id.mustofa.app.academy.util

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import id.mustofa.app.academy.R
import id.mustofa.app.academy.ViewModelFactory
import kotlin.reflect.KClass

/**
 * @author Habib Mustofa
 * Indonesia on 03/08/19
 */
@GlideModule
class ImageModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        )
    }
}

fun ImageView.load(imagePath: String) =
    GlideApp.with(rootView).load(imagePath).into(this)

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModel: KClass<T>) =
    ViewModelProviders.of(this, ViewModelFactory.instance(application))[viewModel.java]