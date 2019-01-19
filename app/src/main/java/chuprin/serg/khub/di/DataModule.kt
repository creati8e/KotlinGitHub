package chuprin.serg.khub.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import chuprin.serg.khub.common.data.DataSource
import chuprin.serg.khub.common.data.database.datasource.CredentialsDbDataSource
import chuprin.serg.khub.common.data.database.datasource.GithubRepositoryDbDataSource
import chuprin.serg.khub.common.data.database.datasource.GithubUserDbDataSource
import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import chuprin.serg.khub.common.data.database.dto.GithubRepositoryDbDto
import chuprin.serg.khub.common.data.database.dto.GithubUserDbDto
import chuprin.serg.khub.common.data.network.api.GithubRepositoriesApi
import chuprin.serg.khub.common.data.network.api.GithubUsersApi
import chuprin.serg.khub.common.data.network.datasource.GithubRepositoryNetworkDataSource
import chuprin.serg.khub.common.data.network.datasource.GithubUserNetworkDataSource
import chuprin.serg.khub.common.data.network.dto.GithubRepositoryNetworkDto
import chuprin.serg.khub.common.data.network.dto.GithubUserNetworkDto
import chuprin.serg.khub.common.data.repository.CredentialsRepository
import chuprin.serg.khub.common.data.repository.GithubRepository
import chuprin.serg.khub.common.data.repository.GithubUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
class DataModule {

    //region Repositories

    @Provides
    @Singleton
    fun provideUserRepository(
        dbDataSource: DataSource<GithubUserDbDto>,
        netDataSource: DataSource<GithubUserNetworkDto>
    ): GithubUserRepository {
        return GithubUserRepository(dbDataSource, netDataSource)
    }

    @Provides
    @Singleton
    fun provideRepoRepository(
        dbDataSource: DataSource<GithubRepositoryDbDto>,
        netDataSource: DataSource<GithubRepositoryNetworkDto>
    ): GithubRepository {
        return GithubRepository(
            dbDataSource,
            netDataSource
        )
    }

    @Provides
    @Singleton
    fun provideCredentialsRepository(
        dataSource: DataSource<GithubAccountDbDto>
    ): CredentialsRepository {
        return CredentialsRepository(dataSource)
    }


    //endregion

    //region Sources

    @Provides
    fun provideUserDbSource(): DataSource<GithubUserDbDto> {
        return GithubUserDbDataSource()
    }

    @Provides
    fun provideUserNetSource(api: GithubUsersApi): DataSource<GithubUserNetworkDto> {
        return GithubUserNetworkDataSource(api)
    }

    @Provides
    fun provideRepoDbSource(): DataSource<GithubRepositoryDbDto> {
        return GithubRepositoryDbDataSource()
    }

    @Provides
    fun provideRepoNetSource(api: GithubRepositoriesApi): DataSource<GithubRepositoryNetworkDto> {
        return GithubRepositoryNetworkDataSource(api)
    }

    @Provides
    fun provideAccountDbSource(): DataSource<GithubAccountDbDto> {
        return CredentialsDbDataSource()
    }

    //endregion

    @Provides
    fun provideShredPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}