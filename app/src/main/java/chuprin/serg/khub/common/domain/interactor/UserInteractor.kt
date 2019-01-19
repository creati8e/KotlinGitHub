package chuprin.serg.khub.common.domain.interactor

import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import chuprin.serg.khub.common.data.database.specification.account.GetActiveAccountSpecification
import chuprin.serg.khub.common.data.database.specification.user.AllUsersSpecification
import chuprin.serg.khub.common.data.database.specification.user.UserLoginSpecification
import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.data.repository.CachePolicy
import chuprin.serg.khub.common.data.repository.CredentialsRepository
import chuprin.serg.khub.common.data.repository.GithubUserRepository
import io.reactivex.Completable
import io.reactivex.Observable

class UserInteractor(
    private val usersRepository: GithubUserRepository,
    private val credentialsRepository: CredentialsRepository
) {

    fun getUsers(): Observable<List<GithubUserEntity>> {
        return usersRepository.getAll(AllUsersSpecification())
    }

    fun getUser(login: String): Observable<GithubUserEntity> {
        return usersRepository.get(UserLoginSpecification(login))
    }

    fun getMe(cachePolicy: CachePolicy = CachePolicy.CACHE): Observable<GithubUserEntity> {
        return usersRepository.get(
            cachePolicy = cachePolicy,
            specification = UserLoginSpecification(currentAccount().login)
        )
    }

    fun logout() = credentialsRepository.delete(currentAccount())

    fun fetchMe(): Completable = getMe(CachePolicy.NETWORK).ignoreElements()

    fun userLoggedIn(): Boolean = currentAccount().token.isNotEmpty()

    private fun currentAccount(): GithubAccountDbDto {
        return credentialsRepository.get(GetActiveAccountSpecification()).blockingFirst()
    }

}