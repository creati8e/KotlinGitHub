package chuprin.serg.kotlin_github.app.domain.users

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.data.repository.CachePolicy
import chuprin.serg.kotlin_github.app.domain.account.AccountInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UsersInteractor @Inject constructor(private val usersRepository: AbsRepository<GithubUserEntity>,
                                          private val accountInteractor: AccountInteractor) {

    fun getUsers(): Observable<List<GithubUserEntity>> = usersRepository.getList(AllUsersSpecification())

    fun getUser(login: String): Observable<GithubUserEntity> = usersRepository.get(UserLoginSpecification(login))

    fun getMe(cachePolicy: CachePolicy = CachePolicy.CACHE_ONLY()): Observable<GithubUserEntity> {
        return usersRepository.get(UserLoginSpecification(accountInteractor.getCurrentAccount().login), cachePolicy)
    }

    fun fetchMe(): Completable = getMe(CachePolicy.NET_ONLY()).ignoreElements()

    fun userLoggedIn(): Boolean = accountInteractor.getCurrentAccount().token.isNotEmpty()

    fun logout() = accountInteractor.logout()
}