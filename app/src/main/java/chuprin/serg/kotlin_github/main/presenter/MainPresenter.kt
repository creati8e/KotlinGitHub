package chuprin.serg.kotlin_github.main.presenter

import chuprin.serg.kotlin_github.app.domain.interactor.users.UsersInteractor
import chuprin.serg.kotlin_github.main.view.MainView
import chuprin.serg.mvpcore.MvpPresenter
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(private val usersInteractor: UsersInteractor) : MvpPresenter<MainView>() {

    override fun onViewAttached() {
        usersInteractor.fetchMe().subscribe({}, {})
    }

    fun viewResumed() = subscribeView(usersInteractor.getMe()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.id == -1) {
                    view.showUserLogin("Unauthorized")
                    view.showUserAvatar("")
                } else {
                    view.showUserLogin(it.login)
                    view.showUserAvatar(it.avatarUrl)
                }
            }, { it.printStackTrace() }))
}