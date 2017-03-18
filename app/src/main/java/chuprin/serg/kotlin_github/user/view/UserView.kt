package chuprin.serg.kotlin_github.user.view

import chuprin.serg.kotlin_github.app.mvp.view.MvpView

interface UserView : MvpView {

    fun showReposCount(count: String)

    fun showFollowersCount(count: String)

    fun showFollowingCount(count: String)

    fun showImage(image: String)

    fun showLogin(login: String)
}