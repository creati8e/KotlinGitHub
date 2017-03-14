package chuprin.serg.kotlin_github.app.domain.interactor

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.RepoEntity
import rx.Observable
import javax.inject.Inject

class ReposInteractor @Inject constructor(private var repository: AbsRepository<RepoEntity>) {

    fun getAll(): Observable<List<RepoEntity>> = repository.getAll()

}