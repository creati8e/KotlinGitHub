package chuprin.serg.kotlin_github.app.di

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.*
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.githubRepository.GithubRepositoriesRepository
import chuprin.serg.kotlin_github.app.data.repository.githubRepository.source.GithubRepositoriesDbSource
import chuprin.serg.kotlin_github.app.data.repository.githubRepository.source.GithubRepositoriesNetworkSource
import chuprin.serg.kotlin_github.app.data.repository.githubUser.GithubUsersRepository
import chuprin.serg.kotlin_github.app.data.repository.githubUser.source.GithubUsersDbSource
import chuprin.serg.kotlin_github.app.data.repository.githubUser.source.GithubUsersNetworkSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = arrayOf(NetworkModule::class))
class DataModule {

    //region Repositories

    @Provides
    @Singleton
    fun provideUserRepository(dbSource: Source<GithubUserDbEntity>,
                              netSource: Source<GithubUserNetworkEntity>)
            : AbsRepository<GithubUserEntity> {

        return GithubUsersRepository(dbSource, netSource)
    }

    @Provides
    @Singleton
    fun provideRepoRepository(dbSource: Source<GithubRepositoryDbEntity>,
                              netSource: Source<GithubRepositoryNetworkEntity>): AbsRepository<GithubRepositoryEntity> {
        return GithubRepositoriesRepository(dbSource, netSource)
    }

    //endregion

    //region Sources

    @Provides
    fun provideUserDbSource(): Source<GithubUserDbEntity> = GithubUsersDbSource()

    @Provides
    fun provideUserNetSource(api: GithubUsersApi): Source<GithubUserNetworkEntity> {
        return GithubUsersNetworkSource(api)
    }

    @Provides
    fun provideRepoDbSource(): Source<GithubRepositoryDbEntity> = GithubRepositoriesDbSource()

    @Provides
    fun provideRepoNetSource(api: GithubRepositoriesApi): Source<GithubRepositoryNetworkEntity> {
        return GithubRepositoriesNetworkSource(api)
    }

    //endregion
}