package chuprin.serg.kotlin_github.app.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.PaginationAbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.*
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsDbSource
import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
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
            : PaginationAbsRepository<GithubUserEntity> {

        return GithubUsersRepository(dbSource, netSource)
    }

    @Provides
    @Singleton
    fun provideRepoRepository(dbSource: Source<GithubRepositoryDbEntity>,
                              netSource: Source<GithubRepositoryNetworkEntity>): AbsRepository<GithubRepositoryEntity> {
        return GithubRepositoriesRepository(dbSource, netSource)
    }

    @Provides
    @Singleton
    fun provideCredentialsRepository(source: Source<GithubAccount>): AbsRepository<GithubAccount> {
        return CredentialsRepository(source)
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

    @Provides
    fun provideAccountDbSource(): Source<GithubAccount> = CredentialsDbSource()

    //endregion

    @Provides
    fun provideShredPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}