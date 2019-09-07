package id.mustofa.app.amber.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.mustofa.app.amber.R
import id.mustofa.app.amber.ViewModelFactory
import kotlin.reflect.KClass

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */

// ---> Glide image loader
@GlideModule
class ImageModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        val loader = CircularProgressDrawable(context).apply {
            strokeWidth = 3f
            centerRadius = 32f
            setColorSchemeColors(Color.YELLOW)
            start()
        }
        val options = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(loader)
            .error(R.drawable.ic_image_error)
        builder.setDefaultRequestOptions(options)
    }
}

fun ImageView.loadTmdbImage(path: String, size: String = "w185") {
    if (path.isNotBlank()) {
        val imagePath = "https://image.tmdb.org/t/p/$size$path"
        GlideApp.with(rootView).load(imagePath).into(this)
    }
}

// ---> Activities
fun Activity.toActivity(clz: KClass<*>, intent: (Intent.() -> Unit)? = null) {
    val activityIntent = Intent(applicationContext, clz.java)
    intent?.invoke(activityIntent)
    startActivity(activityIntent)
}

fun Activity.snackIt(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    parent: View = findViewById(android.R.id.content),
    self: (Snackbar.() -> Unit)? = null
) {
    val snack = Snackbar.make(parent, message, duration)
    self?.invoke(snack)
    snack.show()
}

fun <T : ViewModel> FragmentActivity.obtainViewModel(viewModel: KClass<T>) =
    ViewModelProviders.of(this, ViewModelFactory.instance(application))[viewModel.java]

fun FragmentActivity.snackItObserve(message: LiveData<Int>, lifecycleOwner: LifecycleOwner) {
    message.observe(lifecycleOwner, Observer { it?.let { m -> snackIt(getString(m)) } })
}

// ---> Gson
inline fun <reified T> Gson.fromJsonString(value: String): T =
    this.fromJson(value, object : TypeToken<T>() {}.type)

inline fun <reified T> Gson.toJsonString(value: T): String =
    this.toJson(value, object : TypeToken<T>() {}.type)