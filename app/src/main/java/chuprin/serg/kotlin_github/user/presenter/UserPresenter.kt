package chuprin.serg.kotlin_github.user.presenter

import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.domain.interactor.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.domain.interactor.users.UsersInteractor
import chuprin.serg.kotlin_github.app.presentation.presenter.observeWithProgress
import chuprin.serg.kotlin_github.user.view.UserView
import chuprin.serg.mvpcore.MvpPresenter
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UserPresenter @Inject constructor(private val usersInteractor: UsersInteractor,
                                        private val repositoriesInteractor: RepositoriesInteractor,
                                        private val login: String) : MvpPresenter<UserView>() {

    override fun onViewAttached() {
        subscribeView(usersInteractor.getUser(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ showUser(it) }, ::error))

        subscribeView(repositoriesInteractor.getUserRepositories(login)
                .observeWithProgress(view)
                .subscribe({ view.showRepositories(it) }, { it.printStackTrace() }))
    }

    private fun showUser(user: GithubUserEntity) {
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
}