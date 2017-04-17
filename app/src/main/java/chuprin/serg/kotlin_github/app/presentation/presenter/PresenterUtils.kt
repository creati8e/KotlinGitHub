package chuprin.serg.kotlin_github.app.presentation.presenter

import chuprin.serg.kotlin_github.app.presentation.view.ProgressView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

fun <T> Observable<T>.subscribeWithProgress(v: ProgressView?): Observable<T> {
    return observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { v?.showProgress(true) }
            .doOnUnsubscribe { v?.showProgress(false) }
}

