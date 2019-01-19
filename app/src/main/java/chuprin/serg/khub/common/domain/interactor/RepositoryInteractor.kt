package chuprin.serg.khub.common.domain.interactor

import chuprin.serg.khub.common.data.database.specification.repository.AllRepositoriesSpecification
import chuprin.serg.khub.common.data.database.specification.repository.UserForkedRepositoriesSpecification
import chuprin.serg.khub.common.data.database.specification.repository.UserOwnRepositoriesSpecification
import chuprin.serg.khub.common.data.database.specification.repository.UserRepositoriesSpecification
import chuprin.serg.khub.common.data.entity.GithubRepositoryEntity
import chuprin.serg.khub.common.data.repository.CachePolicy
import chuprin.serg.khub.common.data.repository.GithubRepository
import io.reactivex.Observable

class RepositoryInteractor(
    private var repository: GithubRepository
) {

    fun getAllRepositories(): Observable<List<GithubRepositoryEntity>> {
        return repository.getAll(AllRepositoriesSpecification())
    }

    fun getUserRepositories(login: String): Observable<List<GithubRepositoryEntity>> {
        return repository.getAll(
            UserRepositoriesSpecification(
                login
            )
        )
    }

    fun getUserForkedRepositories(login: String): Observable<List<GithubRepositoryEntity>> {
        return repository.getAll(
            UserForkedRepositoriesSpecification(
                login
            ),
            cachePolicy = CachePolicy.CACHE
        )
    }

    fun getUserOwnRepositories(login: String): Observable<List<GithubRepositoryEntity>> {
        return repository.getAll(
            UserOwnRepositoriesSpecification(
                login
            ),
            cachePolicy = CachePolicy.CACHE
        )
    }

}