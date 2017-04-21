package chuprin.serg.kotlin_github.main.view

import chuprin.serg.mvpcore.view.MvpView

interface MainView : MvpView {
    fun showUserLogin(login: String)

    fun showUserAvatar(url: String)

    fun showLogin()

    fun showLogoutDialog()

    fun showLoggedIn(enabled: Boolean)
}