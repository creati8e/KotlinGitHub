package chuprin.serg.kotlin_github.user.presenter

import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import chuprin.serg.kotlin_github.app.mvp.MvpPresenter
import chuprin.serg.kotlin_github.user.view.UserView
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UserPresenter @Inject constructor(private val usersInteractor: UsersInteractor)
    : MvpPresenter<UserView>() {

    private var login: String = ""

    override fun onViewAttached() {
        subscribeView(usersInteractor.getUser(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ showUser(it) }, ::error))
    }

    private fun showUser(user: UserEntity) {
        view.apply {
            user.apply {
                showFollowersCount(followers.toString())
                showFollowingCount(following.toString())
                showImage(avatarUrl)
                showLogin(login)
                showReposCount(repos.toString())
            }
        }
    }

    fun setUser(login: String) {
        this.login = login
    }
}