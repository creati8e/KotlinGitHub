package chuprin.serg.kotlin_github.app.di

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.*
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.repo.ReposRepository
import chuprin.serg.kotlin_github.app.data.repository.repo.source.RepoDbSource
import chuprin.serg.kotlin_github.app.data.repository.repo.source.RepoNetworkSource
import chuprin.serg.kotlin_github.app.data.repository.user.UsersRepository
import chuprin.serg.kotlin_github.app.data.repository.user.source.UserDbSource
import chuprin.serg.kotlin_github.app.data.repository.user.source.UserNetworkSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = arrayOf(NetworkModule::class))
class DataModule {

    //region Repositories

    @Provides
    @Singleton
    fun provideUserRepository(dbSource: Source<UserDbEntity>, netSource: Source<UserNetworkEntity>)
            : AbsRepository<UserEntity> {

        return UsersRepository(dbSource, netSource)
    }

    @Provides
    @Singleton
    fun provideRepoRepository(dbSource: Source<RepoDbEntity>, netSource: Source<RepoNetworkEntity>): AbsRepository<RepoEntity> {
        return ReposRepository(dbSource, netSource)
    }

    //endregion

    //region Sources

    @Provides
    fun provideUserDbSource(): Source<UserDbEntity> = UserDbSource()

    @Provides
    fun provideUserNetSource(api: GithubUsersApi): Source<UserNetworkEntity> = UserNetworkSource(api)

    @Provides
    fun provideRepoDbSource(): Source<RepoDbEntity> = RepoDbSource()

    @Provides
    fun provideRepoNetSource(api: GithubRepositoriesApi): Source<RepoNetworkEntity> = RepoNetworkSource(api)

    //endregion
}