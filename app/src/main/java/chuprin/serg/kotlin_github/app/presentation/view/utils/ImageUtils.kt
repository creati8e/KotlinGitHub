package chuprin.serg.kotlin_github.app.presentation.view.utils

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.load(url: String, @DrawableRes error: Int) {
    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).error(error).into(this)
}
