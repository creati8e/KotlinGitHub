package chuprin.serg.khub.di

import chuprin.serg.khub.common.data.repository.CredentialsRepository
import chuprin.serg.khub.common.data.repository.GithubRepository
import chuprin.serg.khub.common.data.repository.GithubUserRepository
import chuprin.serg.khub.common.domain.interactor.AccountInteractor
import chuprin.serg.khub.common.domain.interactor.RepositoryInteractor
import chuprin.serg.khub.common.domain.interactor.UserInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideUsersInteractor(
        credentialsRepository: CredentialsRepository,
        usersRepository: GithubUserRepository
    ): UserInteractor {
        return UserInteractor(
            usersRepository,
            credentialsRepository
        )
    }

    @Provides
    @Singleton
    fun provideGithubRepositoriesInteractor(
        repository: GithubRepository
    ): RepositoryInteractor {
        return RepositoryInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideAccountInteractor(repository: CredentialsRepository): AccountInteractor {
        return AccountInteractor(repository)
    }

}
