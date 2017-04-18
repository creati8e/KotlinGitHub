package chuprin.serg.kotlin_github.main.view

import chuprin.serg.mvpcore.view.MvpView

interface MainView : MvpView {
    fun showUser(login: String)
}