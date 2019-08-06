package id.mustofa.app.amber.util

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import id.mustofa.app.amber.R
import kotlin.reflect.KClass

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */

@GlideModule
class ImageModule : AppGlideModule()

fun Activity.toActivity(clz: KClass<*>, extras: ((Intent) -> Unit)? = null) {
    val intent = Intent(applicationContext, clz.java)
    extras?.invoke(intent)
    startActivity(intent)
}

fun AppCompatActivity.bindFragment(
    fragment: Fragment,
    tag: String,
    @IdRes container: Int = android.R.id.content,
    replace: Boolean = false,
    allowStateLoss: Boolean = false
) {
    supportFragmentManager
        .beginTransaction().apply {
            if (replace) replace(container, fragment, tag)
            else add(container, fragment, tag)
        }.run {
            if (allowStateLoss) commitAllowingStateLoss()
            else commit()
        }
}

fun Activity.snackIt(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    parent: View = findViewById(android.R.id.content),
    self: ((Snackbar) -> Unit)? = null
) {
    val snack = Snackbar.make(parent, message, duration)
    self?.invoke(snack)
    snack.show()
}

fun <T> ImageView.load(image: T) {
    val context = rootView.context
    val loader = CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        backgroundColor = R.color.accent
        start()
    }
    val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(loader)
        .error(R.drawable.ic_image_error)

    Glide.with(context).load(image).apply(options).into(this)
}