package chuprin.serg.kotlin_github.app.presentation.view.utils

import android.view.View

fun View.visibility(visible: Boolean) {
    visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

