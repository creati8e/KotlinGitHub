package chuprin.serg.kotlin_github.app.domain.interactor

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import rx.Observable
import javax.inject.Inject

class UsersInteractor @Inject constructor(private var repository: AbsRepository<UserEntity>) {

    fun getUsers(): Observable<List<UserEntity>> = repository.getAll()

    fun getUser(login: String): Observable<UserEntity> = repository.get(login)
}