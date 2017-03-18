package chuprin.serg.kotlin_github.main.repos.presenter

import chuprin.serg.kotlin_github.app.domain.interactor.ReposInteractor
import chuprin.serg.kotlin_github.app.mvp.MvpPresenter
import chuprin.serg.kotlin_github.app.presentation.presenter.subscribeWithProgress
import chuprin.serg.kotlin_github.main.repos.view.ReposView
import javax.inject.Inject

class ReposPresenter @Inject constructor(val interactor: ReposInteractor) : MvpPresenter<ReposView>() {

    override fun onViewAttached() {
        subscribeView(interactor.getAll()
                .subscribeWithProgress(view)
                .subscribe({ view.showData(it) }, { it.printStackTrace() }))
    }
}