package chuprin.serg.kotlin_github.main.users.presenter

import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import chuprin.serg.kotlin_github.app.mvp.MvpPresenter
import chuprin.serg.kotlin_github.app.presentation.presenter.subscribeWithProgress
import chuprin.serg.kotlin_github.main.users.view.UsersView
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class UserListPresenter
@Inject constructor(val interactor: UsersInteractor) : MvpPresenter<UsersView>(), AnkoLogger {

    override fun onViewAttached() {
        subscribe(interactor.getUsers()
                .subscribeWithProgress(view)
                .subscribe({ view?.showData(it) }, { it.printStackTrace() }))
    }
}