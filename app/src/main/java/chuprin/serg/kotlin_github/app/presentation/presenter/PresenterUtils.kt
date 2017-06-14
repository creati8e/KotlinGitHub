package chuprin.serg.kotlin_github.app.presentation.presenter

import chuprin.serg.kotlin_github.app.presentation.view.ProgressView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

fun <T> Observable<T>.observeWithProgress(v: ProgressView?): Observable<T> {
    return observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { v?.showProgress(true) }
            .doOnNext { v?.showProgress(false) }
}

