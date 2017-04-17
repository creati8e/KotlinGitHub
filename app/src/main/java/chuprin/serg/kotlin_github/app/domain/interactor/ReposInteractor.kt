package chuprin.serg.kotlin_github.app.domain.interactor

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import rx.Observable
import javax.inject.Inject

class ReposInteractor @Inject constructor(private var repository: AbsRepository<GithubRepositoryEntity>) {

    fun getAll(): Observable<List<GithubRepositoryEntity>> = repository.getAll()

}