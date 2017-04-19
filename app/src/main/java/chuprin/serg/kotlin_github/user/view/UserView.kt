package chuprin.serg.kotlin_github.user.view

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.presentation.view.ProgressView


interface UserView : ProgressView {

    fun showReposCount(count: String)

    fun showFollowersCount(count: String)

    fun showFollowingCount(count: String)

    fun showImage(image: String)

    fun showLogin(login: String)

    fun showRepositories(repositories: List<GithubRepositoryEntity>)
}