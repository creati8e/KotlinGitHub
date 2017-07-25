package chuprin.serg.kotlin_github.main.view

import serg.chuprin.mvp_core.view.MvpView


interface MainView : MvpView {
    fun showUserLogin(login: String)

    fun showUserAvatar(url: String)

    fun showLogin()

    fun showLogoutDialog()

    fun showLoggedIn(enabled: Boolean)
}