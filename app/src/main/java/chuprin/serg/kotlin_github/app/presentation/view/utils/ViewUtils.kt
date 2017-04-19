package chuprin.serg.kotlin_github.app.presentation.view.utils

import android.view.View
import android.widget.TextView

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

