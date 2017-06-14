package chuprin.serg.kotlin_github.user.presenter

import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.domain.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.domain.users.UsersInteractor
import chuprin.serg.kotlin_github.app.presentation.presenter.observeWithProgress
import chuprin.serg.kotlin_github.user.view.UserView
import io.reactivex.android.schedulers.AndroidSchedulers
import mvp_core.MvpPresenter
import javax.inject.Inject

class UserPresenter @Inject constructor(private val usersInteractor: UsersInteractor,
                                        private val repositoriesInteractor: RepositoriesInteractor,
                                        private val login: String) : MvpPresenter<UserView>() {

    override fun onViewAttached() {
        subscribeView(usersInteractor.getUser(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ showUser(it) }, { it.printStackTrace() }))

        subscribeView(repositoriesInteractor.getUserRepositories(login)
                .observeWithProgress(view)
                .subscribe({ view.showRepositories(it) }, { it.printStackTrace() }))
    }

    private fun showUser(user: GithubUserEntity) {
        with(view) {
            with(user) {
                showFollowersCount(followers.toString())
                showFollowingCount(following.toString())
                showImage(avatarUrl)
                showLogin(login)
                showReposCount(repos.toString())
            }
        }
    }
}