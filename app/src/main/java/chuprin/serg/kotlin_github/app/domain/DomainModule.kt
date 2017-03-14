import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.RepoEntity
import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import chuprin.serg.kotlin_github.app.domain.interactor.ReposInteractor
import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGitHubUserInteractor(userRepository: AbsRepository<UserEntity>): UsersInteractor {
        return UsersInteractor(userRepository)
    }

    @Provides
    fun provideGithubRepoInteractor(repository: AbsRepository<RepoEntity>): ReposInteractor {
        return ReposInteractor(repository)
    }
}
