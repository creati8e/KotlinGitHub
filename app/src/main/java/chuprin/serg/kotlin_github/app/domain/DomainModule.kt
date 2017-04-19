import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
import chuprin.serg.kotlin_github.app.domain.interactor.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.domain.interactor.users.UsersInteractor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGitHubUserInteractor(usersRepository: AbsRepository<GithubUserEntity>,
                                    credentialsRepository: CredentialsRepository): UsersInteractor {
        return UsersInteractor(usersRepository, credentialsRepository)
    }

    @Provides
    fun provideGithubRepoInteractor(repository: AbsRepository<GithubRepositoryEntity>): RepositoriesInteractor {
        return RepositoriesInteractor(repository)
    }
}
