import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.PaginationAbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.domain.account.AccountInteractor
import chuprin.serg.kotlin_github.app.domain.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.domain.users.UsersInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGitHubUserInteractor(usersRepository: PaginationAbsRepository<GithubUserEntity>,
                                    accountInteractor: AccountInteractor): UsersInteractor {
        return UsersInteractor(usersRepository, accountInteractor)
    }

    @Provides
    @Singleton
    fun provideGithubRepoInteractor(repository: AbsRepository<GithubRepositoryEntity>): RepositoriesInteractor {
        return RepositoriesInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideAccountInteractor(repository: AbsRepository<GithubAccount>) = AccountInteractor(repository)
}
