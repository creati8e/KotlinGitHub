package chuprin.serg.kotlin_github.main.presenter

import chuprin.serg.kotlin_github.app.domain.users.UsersInteractor
import chuprin.serg.kotlin_github.main.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import mvp_core.MvpPresenter
import javax.inject.Inject

class MainPresenter
@Inject constructor(private val usersInteractor: UsersInteractor) : MvpPresenter<MainView>() {

    override fun onViewAttached() {
        usersInteractor.fetchMe().subscribe({}, {})
    }

    fun viewResumed() = loadUser()

    fun loginBtnClicked() = when (usersInteractor.userLoggedIn()) {
        false -> view.showLogin()
        true -> view.showLogoutDialog()
    }

    fun logout() {
        usersInteractor.logout()
        loadUser()
    }

    private fun loadUser() {
        subscribeView(usersInteractor.getMe()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.id == -1) {
                        view.showUserLogin("Unauthorized")
                        view.showUserAvatar("")
                        view.showLoggedIn(true)
                    } else {
                        view.showUserLogin(it.login)
                        view.showUserAvatar(it.avatarUrl)
                        view.showLoggedIn(false)
                    }
                }, { it.printStackTrace() }))
    }

}