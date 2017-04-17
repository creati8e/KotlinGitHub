import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.domain.interactor.ReposInteractor
import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGitHubUserInteractor(repository: AbsRepository<GithubUserEntity>): UsersInteractor {
        return UsersInteractor(repository)
    }

    @Provides
    fun provideGithubRepoInteractor(repository: AbsRepository<GithubRepositoryEntity>): ReposInteractor {
        return ReposInteractor(repository)
    }
}
