package chuprin.serg.kotlin_github.main.users.view

import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.main.view.ListView

interface UsersListView : ListView<GithubUserEntity> {

    fun paginationProgress(visible: Boolean)

    fun showNetworkError(visible: Boolean)
}