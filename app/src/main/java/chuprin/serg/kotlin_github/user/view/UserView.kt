package chuprin.serg.kotlin_github.user.view

import chuprin.serg.kotlin_github.app.mvp.view.MvpView
import chuprin.serg.kotlin_github.user.presenter.UserPresenter

interface UserView : MvpView<UserPresenter> {

    fun showReposCount(count: String)

    fun showFollowersCount(count: String)

    fun showFollowingCount(count: String)

    fun showImage(image: String)

    fun showLogin(login: String)
}