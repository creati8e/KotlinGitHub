package chuprin.serg.kotlin_github.main.repositories.presenter

import chuprin.serg.kotlin_github.app.domain.interactor.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.presentation.presenter.subscribeWithProgress
import chuprin.serg.kotlin_github.main.repositories.view.RepositoriesListView
import chuprin.serg.mvpcore.MvpPresenter
import javax.inject.Inject

class RepositoriesListPresenter @Inject constructor(val interactor: RepositoriesInteractor) : MvpPresenter<RepositoriesListView>() {

    override fun onViewAttached() {
        subscribeView(interactor.getAll()
                .subscribeWithProgress(view)
                .subscribe({ view.showData(it) }, { it.printStackTrace() }))
    }
}