package chuprin.serg.kotlin_github.main.users.presenter

import chuprin.serg.kotlin_github.app.domain.users.UsersInteractor
import chuprin.serg.kotlin_github.app.presentation.presenter.observeWithProgress
import chuprin.serg.kotlin_github.main.users.view.UsersListView
import chuprin.serg.mvpcore.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class UsersListPresenter
@Inject constructor(val interactor: UsersInteractor) : MvpPresenter<UsersListView>(), AnkoLogger {

    override fun onViewAttached() {
        subscribeView(interactor.getUsers()
                .observeWithProgress(view)
                .subscribe({ view.showData(it) }, { it.printStackTrace() }))
    }
}