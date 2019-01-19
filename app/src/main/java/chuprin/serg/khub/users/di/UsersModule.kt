package chuprin.serg.khub.users.di

import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.data.repository.GithubUserRepository
import chuprin.serg.khub.common.domain.pagintation.Paginator
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.users.model.UsersListViewModel
import chuprin.serg.khub.users.model.paginator.UsersPaginator
import dagger.Module
import dagger.Provides

@Module
class UsersModule {

    @Provides
    @PerView
    fun provideUsersListViewModel(paginator: Paginator<GithubUserEntity>): UsersListViewModel {
        return UsersListViewModel(paginator)
    }

    @Provides
    @PerView
    fun provideUsersPaginator(
        repository: GithubUserRepository
    ): Paginator<GithubUserEntity> {
        return UsersPaginator(repository)
    }

}