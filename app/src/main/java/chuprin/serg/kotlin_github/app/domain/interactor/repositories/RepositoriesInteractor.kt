package chuprin.serg.kotlin_github.app.domain.interactor.repositories

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import rx.Observable
import javax.inject.Inject

class RepositoriesInteractor @Inject constructor(private var repository: AbsRepository<GithubRepositoryEntity>) {

    fun getAll(): Observable<List<GithubRepositoryEntity>> = repository.getList(AllRepositoriesSpecification())

}