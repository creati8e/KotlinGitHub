package chuprin.serg.kotlin_github.app.domain.interactor.users

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import rx.Observable
import javax.inject.Inject

class UsersInteractor @Inject constructor(private var repository: AbsRepository<GithubUserEntity>) {

    fun getUsers(): Observable<List<GithubUserEntity>> = repository.getList(AllUsersSpecification())

    fun getUser(login: String): Observable<GithubUserEntity> = repository.get(UserLoginSpecification(login))
}