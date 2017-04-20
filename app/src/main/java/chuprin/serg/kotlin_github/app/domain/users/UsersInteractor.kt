package chuprin.serg.kotlin_github.app.domain.users

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.data.repository.CachePolicy
import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
import rx.Completable
import rx.Observable
import javax.inject.Inject

class UsersInteractor @Inject constructor(private var usersRepository: AbsRepository<GithubUserEntity>,
                                          private val credentialsRepository: CredentialsRepository) {

    fun getUsers(): Observable<List<GithubUserEntity>> = usersRepository.getList(AllUsersSpecification())

    fun getUser(login: String): Observable<GithubUserEntity> = usersRepository.get(UserLoginSpecification(login))

    fun getMe(cachePolicy: CachePolicy = CachePolicy.CACHE_ONLY()): Observable<GithubUserEntity> {
        val meSpecification = GetMeSpecification(credentialsRepository.getMyId())
        return usersRepository.get(meSpecification, cachePolicy)
    }

    fun fetchMe(): Completable = getMe(CachePolicy.NET_ONLY()).toCompletable()

    fun userLoggedIn(): Boolean = credentialsRepository.getToken().isNotEmpty()

    fun logout() = credentialsRepository.clear()
}