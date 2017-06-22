package chuprin.serg.kotlin_github.main.users

import chuprin.serg.kotlin_github.app.data.PaginationAbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.app.domain.pagintation.Paginator
import chuprin.serg.kotlin_github.main.users.model.UsersPaginator
import chuprin.serg.kotlin_github.main.users.presenter.UsersListPresenter
import dagger.Module
import dagger.Provides

@Module
class UsersModule {

    @Provides
    @PerView
    fun provideUsersPresenter(paginator: Paginator<GithubUserEntity>): UsersListPresenter {
        return UsersListPresenter(paginator)
    }

    @Provides
    @PerView
    fun provideUsersPaginator(repository: PaginationAbsRepository<GithubUserEntity>): Paginator<GithubUserEntity> {
        return UsersPaginator(repository)
    }
}