package chuprin.serg.kotlin_github.app.domain.repositories

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import rx.Observable
import javax.inject.Inject

class RepositoriesInteractor @Inject constructor(private var repository: AbsRepository<GithubRepositoryEntity>) {

    fun getAllRepositories(): Observable<List<GithubRepositoryEntity>> {
        return repository.getList(AllRepositoriesSpecification())
    }

    fun getUserRepositories(login: String): Observable<List<GithubRepositoryEntity>> {
        return repository.getList(UserRepositoriesSpecification(login))
    }

}