package chuprin.serg.kotlin_github.app.presentation.view

import android.support.annotation.StringRes
import chuprin.serg.kotlin_github.app.mvp.view.MvpView

interface ProgressView : MvpView {
    fun showProgress(visible: Boolean)

    fun showMessage(@StringRes message: Int)
}