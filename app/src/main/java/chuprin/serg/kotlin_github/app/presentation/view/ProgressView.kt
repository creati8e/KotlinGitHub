package chuprin.serg.kotlin_github.app.presentation.view

import android.support.annotation.StringRes
import chuprin.serg.mvpcore.view.MvpView

interface ProgressView : MvpView {
    fun showProgress(visible: Boolean)

    fun showMessage(@StringRes message: Int)
}