package chuprin.serg.kotlin_github.app.presentation.view.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.bundleOf

fun View.visibility(visible: Boolean) {
    visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

fun TextView.setTextOrHide(text: String) = when {
    text.isEmpty() -> visibility(false)
    else -> this.text = text
}


inline fun <reified T : Fragment> instanceOf(vararg params: Pair<String, Any>): T
        = T::class.java.newInstance()
        .apply {
            arguments = bundleOf(*params)
        }

fun Bundle.putAll(vararg bundles: Bundle?): Bundle = Bundle()
        .apply {
            bundles.filterNotNull().forEach { putAll(it) }
        }




